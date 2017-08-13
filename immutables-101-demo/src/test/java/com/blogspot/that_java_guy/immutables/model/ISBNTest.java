package com.blogspot.that_java_guy.immutables.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class ISBNTest {

	@Test(expected = IllegalStateException.class)
	public void validation1() throws Exception {
		ImmutableISBN.builder()
				.number( "1234567890" )
				.build();
	}

	@Test(expected = IllegalStateException.class)
	public void validation2() throws Exception {
		ImmutableISBN.builder()
				.number( "23" )
				.build();
	}

	@Test(expected = IllegalStateException.class)
	public void validation3() throws Exception {
		ImmutableISBN.builder()
				.number( "5-02-013850-5" )
				.build();
	}

	@Test(expected = NullPointerException.class)
	public void validation4() throws Exception {
		ImmutableISBN.builder()
				.number( null )
				.build();
	}

	@Test
	public void goodTest() throws Exception {
		ISBN isbn = ImmutableISBN.builder()
				.number( "5-02-013850-9" )
				.build();

		assertThat( isbn ).hasFieldOrPropertyWithValue( "number", "5-02-013850-9" );

	}
}