package com.blogspot.that_java_guy;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Marko Bekhta
 */
@Entity
@Table(name = "actor")
public class Actor extends BaseEntity {

	private Name name;

	protected Actor() {
	}

	private Actor(Name name) {
		this.name = name;
	}

	public static Actor with(Name name) {
		return new Actor( name );
	}

	public Name getName() {
		return name;
	}
}
