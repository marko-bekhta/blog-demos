package com.blogspot.that_java_guy;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.blogspot.that_java_guy.constants.Grade;
import com.blogspot.that_java_guy.converters.GradeConverter;

/**
 * @author Marko Bekhta
 */

@Entity
@Table(name = "test_exam")
public class TestExam {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number_of_passed_questions")
	private int numberOfPassedQuestions;

	@Convert(converter = GradeConverter.class)
	@Column(name = "exam_grade")
	private Grade examGrade;

	@OneToOne(cascade = CascadeType.PERSIST)
	private Person student;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private List<Question> questions;

	public TestExam() {
	}

	public TestExam(int numberOfPassedQuestions, Grade examGrade, Person student, List<Question> questions) {
		this.numberOfPassedQuestions = numberOfPassedQuestions;
		this.examGrade = examGrade;
		this.student = student;
		this.questions = questions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfPassedQuestions() {
		return numberOfPassedQuestions;
	}

	public void setNumberOfPassedQuestions(int numberOfPassedQuestions) {
		this.numberOfPassedQuestions = numberOfPassedQuestions;
	}

	public Grade getExamGrade() {
		return examGrade;
	}

	public void setExamGrade(Grade examGrade) {
		this.examGrade = examGrade;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
