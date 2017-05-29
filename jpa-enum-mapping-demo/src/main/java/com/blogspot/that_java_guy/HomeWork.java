package com.blogspot.that_java_guy;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.blogspot.that_java_guy.constants.Grade;

/**
 * @author Marko Bekhta
 */

@Entity
@Table(name = "home_work")
public class HomeWork {

	@Id
	@GeneratedValue
	private Long id;

	private String subject;

	@Transient
	private Grade grade;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Person student;

	public HomeWork() {
	}

	public HomeWork(String subject, Grade grade, Person student) {
		this.subject = subject;
		this.grade = grade;
		this.student = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Person getStudent() {
		return student;
	}

	public void setStudent(Person student) {
		this.student = student;
	}

	@Column(name = "grade")
	@Access(AccessType.PROPERTY)
	protected Character getDbGrade() {
		return grade != null ? grade.charGrade : null;
	}

	protected void setDbGrade(Character grade) {
		this.grade = Grade.byChar( grade );
	}
}
