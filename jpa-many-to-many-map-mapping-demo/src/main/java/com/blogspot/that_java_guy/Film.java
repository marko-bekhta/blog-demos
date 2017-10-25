package com.blogspot.that_java_guy;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Marko Bekhta
 */

@Entity
@Table(name = "film")
public class Film extends BaseEntity {

	private String name;

	private Duration length;

	@ElementCollection
	@CollectionTable(name = "film_to_actor",
			joinColumns = @JoinColumn(name = "film_id")
	)
	@MapKeyJoinColumn(name = "actor_id")
	private Map<Actor, CharacterInformation> characterInformation = new HashMap<>();

	protected Film() {
	}

	private Film(String name, Duration length) {
		this.name = name;
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public Duration getLength() {
		return length;
	}

	public Film addCharacter(Actor actor, CharacterInformation information) {
		characterInformation.put( actor, information );
		return this;
	}

	/**
	 * @return a collection of actors that play in the film
	 */
	public Collection<Actor> listActors() {
		return characterInformation.keySet().stream()
				.collect( Collectors.collectingAndThen( Collectors.toSet(), Collections::unmodifiableSet ) );
	}

	/**
	 * @return a collection of characters in the film
	 */
	public Collection<CharacterInformation> listCharacters() {
		return characterInformation.values().stream()
				.collect( Collectors.collectingAndThen( Collectors.toSet(), Collections::unmodifiableSet ) );
	}

	/**
	 * Removes an actor form the film.
	 *
	 * @param actor an actor to remove
	 *
	 * @return {@link CharacterInformation} wrapped in {@link Optional}. Will be empty if there was
	 * no such actor in the film, will contain the actor's role otherwise.
	 */
	public Optional<CharacterInformation> removeActor(Actor actor) {
		return Optional.ofNullable( characterInformation.remove( actor ) );
	}

	public static class Builder {
		private String name;
		private Duration length;

		public Builder title(String name) {
			this.name = name;
			return this;
		}

		public Builder lenth(Duration length) {
			this.length = length;
			return this;
		}

		public Film create() {
			return new Film( name, length );
		}

		public static Builder instance() {
			return new Builder();
		}
	}
}
