package com.blogspot.that_java_guy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.blogspot.that_java_guy.constants.Gender;
import com.blogspot.that_java_guy.constants.Month;

/**
 * @author Marko Bekhta
 */

public class PersonEntityTest extends AbstractEntityTest {

	@Test
	public void testEnum() {
		Person person = new Person( "John", "Doe", Gender.MALE, Month.AUGUST );

		entityManager.persist( person );
		entityManager.flush();

		assertNotNull( "entity not found", entityManager.find( Person.class, person.getId() ) );

		Object[] resultRow = (Object[]) entityManager
				.createNativeQuery( "SELECT p.first_name, p.last_name, p.gender, p.favorite_month FROM person AS p WHERE p.id = :id" )
				.setParameter( "id", person.getId() )
				.getSingleResult();

		assertEquals( "first name should match", "John", resultRow[0] );
		assertEquals( "last name should match", "Doe", resultRow[1] );
		assertEquals( "gender should match", Gender.MALE.name(), resultRow[2] );
		assertEquals( "month should match", Month.AUGUST.ordinal(), resultRow[3] );

	}

}
