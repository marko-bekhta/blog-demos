package com.blogspot.that_java_guy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import com.blogspot.that_java_guy.constants.QuestionKind;

/**
 * @author Marko Bekhta
 */

@Entity
public class Question {

	@Id
	@GeneratedValue
	private Long id;

	private String question;

	@Column(name = "kind", length = 1024)
	@Type(type = "com.blogspot.that_java_guy.converters.type.QuestionKindType")
	private QuestionKind questionKind;

	public Question() {
	}

	public Question(String question, QuestionKind questionKind) {
		this.question = question;
		this.questionKind = questionKind;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public QuestionKind getQuestionKind() {
		return questionKind;
	}

	public void setQuestionKind(QuestionKind questionKind) {
		this.questionKind = questionKind;
	}
}
