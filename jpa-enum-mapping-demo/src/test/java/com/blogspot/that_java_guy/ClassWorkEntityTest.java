package com.blogspot.that_java_guy;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blogspot.that_java_guy.constants.Gender;
import com.blogspot.that_java_guy.constants.Grade;
import com.blogspot.that_java_guy.constants.Month;

/**
 * @author Marko Bekhta
 */
public class ClassWorkEntityTest extends AbstractEntityTest {

	@Test
	public void testEnum() {
		ClassWork homeWork = new ClassWork(
				"Learn JPA enum mappings",
				Grade.GRADE_B,
				new Person( "Angie", "Doe", Gender.FEMALE, Month.APRIL )
		);

		entityManager.persist( homeWork );
		entityManager.flush();

		Object[] result = (Object[]) entityManager.createNativeQuery( "SELECT h.subject, h.grade FROM class_work AS h WHERE h.id = :id" )
				.setParameter( "id", homeWork.getId() )
				.getSingleResult();

		assertEquals( "Learn JPA enum mappings", result[0] );
		assertEquals( Grade.GRADE_B.charGrade.toString(), result[1] );
	}

}
