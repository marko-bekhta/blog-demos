package com.blogspot.that_java_guy.immutables.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class BookTest {

	@Test
	public void bookManipulations() throws Exception {
		Book firstBook = ImmutableBook.builder()
				.title( "Immutables in Java" )
				.iSBN( ImmutableISBN.builder().number( "5-02-013850-9" ).build() )
				.author(
						ImmutableAuthor.builder()
								.name( "John" )
								.surname( "Smith" )
								.build()
				).build();
		// creates a new book with same author and ISBN but new title
		Book secondBook = ( (ImmutableBook) firstBook ).withTitle( "Immutables in Java. Second edition" );

		// as the title is changed the books are different
		assertThat( firstBook ).isNotEqualTo( secondBook );
		// but author and ISBN are still equals
		assertThat( firstBook.getAuthor() ).isEqualTo( secondBook.getAuthor() );
		assertThat( firstBook.getISBN() ).isEqualTo( secondBook.getISBN() );

		// but if you try to use the same value - you'll receive the same object:
		assertThat( firstBook ).isEqualTo( ( (ImmutableBook) firstBook ).withTitle( "Immutables in Java" ) );

	}

}