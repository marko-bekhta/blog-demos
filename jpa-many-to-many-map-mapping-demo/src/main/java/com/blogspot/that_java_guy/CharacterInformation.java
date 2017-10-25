package com.blogspot.that_java_guy;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Marko Bekhta
 */

@Embeddable
public class CharacterInformation implements Serializable {

	private Name name;

	@Column(name = "main_character")
	private boolean mainCharacter;

	public Name getName() {
		return name;
	}

	public boolean isMainCharacter() {
		return mainCharacter;
	}

	protected CharacterInformation() {
	}

	public CharacterInformation(Name name, boolean mainCharacter) {
		this.name = name;
		this.mainCharacter = mainCharacter;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		CharacterInformation that = (CharacterInformation) o;
		return mainCharacter == that.mainCharacter &&
				Objects.equals( name, that.name );
	}

	@Override
	public int hashCode() {
		return Objects.hash( name, mainCharacter );
	}

	public static class Builder {
		private Name name;
		private boolean mainCharacter;

		public Builder withName(Name name) {
			this.name = name;
			return this;
		}

		public Builder isMainCharacter(boolean mainCharacter) {
			this.mainCharacter = mainCharacter;
			return this;
		}

		public CharacterInformation create() {
			return new CharacterInformation( name, mainCharacter );
		}

		public static Builder instance() {
			return new Builder();
		}
	}
}
