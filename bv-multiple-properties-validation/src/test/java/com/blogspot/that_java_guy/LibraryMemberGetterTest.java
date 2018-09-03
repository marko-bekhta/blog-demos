package com.blogspot.that_java_guy;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.constraints.AssertTrue;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
public class LibraryMemberGetterTest extends AbstractValidationTest {

	@Test
	public void testValidation() {
		LibraryMember member = new LibraryMember(
				"John Doe",
				LocalDate.now().plusDays( 10 ),
				Book.getListOfBooks()
		);

		Set<ConstraintViolation<LibraryMember>> violations = validator.validate( member );

		// check there's only one violation
		assertThat( violations ).hasSize( 1 );
		ConstraintViolation<LibraryMember> violation = violations.iterator().next();
		// check that it's a violation of AssertTrue and that message is correct
		assertThat( violation.getConstraintDescriptor().getAnnotation().annotationType() )
				.isEqualTo( AssertTrue.class );
		assertThat( violation.getMessage() )
				.isEqualTo( "Member has incorrect amount of books taken from library" );
	}

	@Test
	public void testValidationSubClass() {
		LibraryMember member = new ExtendedLibraryMember(
				"John Doe",
				LocalDate.now().plusDays( 10 ),
				Book.getListOfBooks()
		);

		Set<ConstraintViolation<LibraryMember>> violations = validator.validate( member );

		// check there's only one violation
		assertThat( violations ).hasSize( 1 );
	}

	static class LibraryMember {
		private final String name;
		private final LocalDate endOfTrialPeriod;
		private final List<Book> books;

		public LibraryMember(String name, LocalDate endOfTrialPeriod, List<Book> books) {
			this.name = name;
			this.endOfTrialPeriod = endOfTrialPeriod;
			this.books = books;
		}

		@AssertTrue(message = "Member has incorrect amount of books taken from library")
		private boolean isValidMember() {
			if ( LocalDate.now().isBefore( endOfTrialPeriod ) ) {
				return books.size() < 2;
			}
			else {
				return !books.isEmpty();
			}
		}

		public String getName() {
			return this.name;
		}

		public LocalDate getEndOfTrialPeriod() {
			return this.endOfTrialPeriod;
		}

		public List<Book> getBooks() {
			return this.books;
		}
	}

	static class ExtendedLibraryMember extends LibraryMember {
		public ExtendedLibraryMember(String name, LocalDate endOfTrialPeriod, List<Book> books) {
			super( name, endOfTrialPeriod, books );
		}
	}

}