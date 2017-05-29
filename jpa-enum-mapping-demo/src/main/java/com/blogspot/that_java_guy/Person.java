package com.blogspot.that_java_guy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.blogspot.that_java_guy.constants.Gender;
import com.blogspot.that_java_guy.constants.Month;

/**
 * @author Marko Bekhta
 */

@Entity
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "favorite_month")
	private Month favoriteMonth;

	public Person() {
	}

	public Person(String firstName, String lastName, Gender gender, Month favoriteMonth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.favoriteMonth = favoriteMonth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Month getFavoriteMonth() {
		return favoriteMonth;
	}

	public void setFavoriteMonth(Month favoriteMonth) {
		this.favoriteMonth = favoriteMonth;
	}
}
