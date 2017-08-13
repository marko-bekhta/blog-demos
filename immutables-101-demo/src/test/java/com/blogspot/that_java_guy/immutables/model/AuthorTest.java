package com.blogspot.that_java_guy.immutables.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class AuthorTest {

	@Test
	public void simpleTest() {
		Author author = ImmutableAuthor.builder()
				.name( "John" )
				.surname( "Smith" )
				.build();
		assertThat( author )
				.hasFieldOrPropertyWithValue( "name", "John" )
				.hasFieldOrPropertyWithValue( "surname", "Smith" );
		assertThat( author.toString() ).isEqualTo( "Author{name=John, surname=Smith}" );
	}
}