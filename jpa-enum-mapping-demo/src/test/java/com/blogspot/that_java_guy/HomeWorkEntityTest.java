package com.blogspot.that_java_guy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.blogspot.that_java_guy.constants.Gender;
import com.blogspot.that_java_guy.constants.Grade;
import com.blogspot.that_java_guy.constants.Month;

/**
 * @author Marko Bekhta
 */
class HomeWorkEntityTest extends AbstractEntityTest {

	@Test
	public void testEnum() {
		HomeWork homeWork = new HomeWork(
				"Learn JPA enum mappings",
				Grade.GRADE_B,
				new Person( "Angie", "Doe", Gender.FEMALE, Month.APRIL )
		);

		entityManager.persist( homeWork );
		entityManager.flush();

		Object[] result = (Object[]) entityManager.createNativeQuery( "SELECT h.subject, h.grade FROM home_work AS h WHERE h.id = :id" )
				.setParameter( "id", homeWork.getId() )
				.getSingleResult();

		Assertions.assertEquals( "Learn JPA enum mappings", result[0] );
		Assertions.assertEquals( Grade.GRADE_B.charGrade.toString(), result[1] );
	}

}
