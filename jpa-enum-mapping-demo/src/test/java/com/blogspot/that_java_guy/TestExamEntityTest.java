package com.blogspot.that_java_guy;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import com.blogspot.that_java_guy.constants.Gender;
import com.blogspot.that_java_guy.constants.Grade;
import com.blogspot.that_java_guy.constants.Month;

/**
 * @author Marko Bekhta
 */
public class TestExamEntityTest extends AbstractEntityTest {

	@Test
	public void testEnum() {
		TestExam testExam = new TestExam(
				10,
				Grade.GRADE_A,
				new Person( "Henry", "Smith", Gender.MALE, Month.DECEMBER ),
				Collections.emptyList()
		);

		entityManager.persist( testExam );
		entityManager.flush();

		Object result = entityManager
				.createNativeQuery( "SELECT t.exam_grade FROM test_exam AS t WHERE t.id = :id" )
				.setParameter( "id", testExam.getId() )
				.getSingleResult();

		assertEquals( Grade.GRADE_A.charGrade.toString(), result );
	}

}
