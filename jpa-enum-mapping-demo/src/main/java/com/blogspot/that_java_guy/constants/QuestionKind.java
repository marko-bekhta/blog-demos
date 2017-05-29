package com.blogspot.that_java_guy.constants;

import java.util.stream.Stream;

/**
 * @author Marko Bekhta
 */
public enum QuestionKind {

	MULTIPLE_CHOICE_YES_NO( 1, "multiple choice of yes/no options", AnswerKind.BOOLEAN, true ),
	SINGLE_YES_NO( 2, "simple yes/no question", AnswerKind.BOOLEAN, false ),
	FREE_TEXT( 3, "a long answer in a free text form", AnswerKind.TEXT, false ),
	CALCULATION( 4, "a result of performed calculus operations", AnswerKind.NUMBER, false ),
	MULTIPLE_CALCULATION( 5, "multiple results of performed calculus operations", AnswerKind.NUMBER, true ),;

	private int id;
	private String questionTypeDescription;
	private AnswerKind answerKind;
	private boolean multipleAnswersPossible;

	QuestionKind(int id, String questionTypeDescription, AnswerKind answerKind, boolean multipleAnswersPossible) {
		this.id = id;
		this.questionTypeDescription = questionTypeDescription;
		this.answerKind = answerKind;
		this.multipleAnswersPossible = multipleAnswersPossible;
	}

	public int getId() {
		return id;
	}

	public String getQuestionTypeDescription() {
		return questionTypeDescription;
	}

	public AnswerKind getAnswerKind() {
		return answerKind;
	}

	public boolean isMultipleAnswersPossible() {
		return multipleAnswersPossible;
	}

	public static QuestionKind byId(int id) {
		return Stream.of( values() )
				.filter( kind -> kind.id == id )
				.findAny()
				.orElse( null );
	}

	public enum AnswerKind {
		TEXT, NUMBER, BOOLEAN, CHARACTER,;
	}
}
