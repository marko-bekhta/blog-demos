package com.blogspot.that_java_guy.immutables.model;

import org.assertj.core.util.Preconditions;
import org.immutables.value.Value;

/**
 * @author Marko Bekhta
 */
@Value.Immutable
public abstract class ISBN {

	public abstract String getNumber();

	@Value.Check
	protected void check() {
		Preconditions.checkState( getNumber() != null,
				"'ISBN number' cannot be null"
		);
		Preconditions.checkState( getNumber().length() >= 10,
				"'ISBN number' should have at least ten chars"
		);
		Preconditions.checkState( isValidISBN10( getNumber() ),
				"'ISBN number' should be a valid ISBN number"
		);
	}

	private static boolean isValidISBN10(final String isbn) {
		String digits = isbn.replaceAll( "[^\\d]", "" );
		if ( digits.length() != 10 ) {
			return false;
		}
		int sum = 0;
		for ( int i = 0; i < digits.length() - 1; i++ ) {
			sum += ( digits.charAt( i ) - '0' ) * ( i + 1 );
		}
		char checkSum = digits.charAt( 9 );
		return sum % 11 == ( checkSum == 'X' ? 10 : checkSum - '0' );
	}
}
