package com.blogspot.that_java_guy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

		Assertions.assertNotNull( entityManager.find( Person.class, person.getId() ), "entity not found" );

		Object[] resultRow = (Object[]) entityManager
				.createNativeQuery( "SELECT p.first_name, p.last_name, p.gender, p.favorite_month FROM person AS p WHERE p.id = :id" )
				.setParameter( "id", person.getId() )
				.getSingleResult();

		Assertions.assertEquals( "John", resultRow[0], "first name should match" );
		Assertions.assertEquals( "Doe", resultRow[1], "last name should match" );
		Assertions.assertEquals( Gender.MALE.name(), resultRow[2], "gender should match" );
		Assertions.assertEquals( Month.AUGUST.ordinal(), resultRow[3], "month should match" );

	}

}
