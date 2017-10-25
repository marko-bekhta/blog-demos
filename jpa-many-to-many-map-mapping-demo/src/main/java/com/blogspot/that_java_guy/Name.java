package com.blogspot.that_java_guy;

import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Marko Bekhta
 */

@Embeddable
@Access(AccessType.FIELD)
public class Name {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	protected Name() {
	}

	private Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return String.join( " ", firstName, lastName );
	}

	public static Name from(String firstName, String lastName) {
		return new Name( firstName, lastName );
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		Name name = (Name) o;
		return Objects.equals( firstName, name.firstName ) &&
				Objects.equals( lastName, name.lastName );
	}

	@Override
	public int hashCode() {
		return Objects.hash( firstName, lastName );
	}
}
