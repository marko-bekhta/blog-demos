package com.blogspot.that_java_guy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.blogspot.that_java_guy.constants.QuestionKind;

/**
 * @author Marko Bekhta
 */

public class QuestionEntityTest extends AbstractEntityTest {

	@Test
	public void testEnum() {
		Question question = new Question(
				"How much is 2 + 2 = ?",
				QuestionKind.CALCULATION
		);

		entityManager.persist( question );
		entityManager.flush();

		Object resultRow = entityManager
				.createNativeQuery( "SELECT q.kind FROM question AS q WHERE q.id = :id" )
				.setParameter( "id", question.getId() )
				.getSingleResult();
		System.err.println( resultRow );
		assertEquals(
				"JSON should match",
				"{\"id\":4,\"description\":\"a result of performed calculus operations\",\"answerKind\":\"NUMBER\",\"multipleAnswers\":false}"
				, resultRow

		);

	}

}
