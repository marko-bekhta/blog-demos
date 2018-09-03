package com.blogspot.that_java_guy;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
public class LibraryMemberCustomConstraintTest extends AbstractValidationTest {

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
		// check that it's a violation of ScriptAssert and that message is correct
		assertThat( violation.getConstraintDescriptor().getAnnotation().annotationType() )
				.isEqualTo( ValidLibraryMember.class );
		assertThat( violation.getMessage() )
				.isEqualTo( "Member has incorrect amount of books taken from library" );
	}

	@ValidLibraryMember
	public static class LibraryMember {
		public final String name;
		public final LocalDate endOfTrialPeriod;
		public final List<Book> books;

		public LibraryMember(String name, LocalDate endOfTrialPeriod, List<Book> books) {
			this.name = name;
			this.endOfTrialPeriod = endOfTrialPeriod;
			this.books = books;
		}
	}

	public static class ValidLibraryMemberValidator implements ConstraintValidator<ValidLibraryMember, LibraryMember> {

		@Override
		public boolean isValid(LibraryMember member, ConstraintValidatorContext context) {
			// null values are considered valid.
			if ( member == null ) {
				return true;
			}

			if ( LocalDate.now().isBefore( member.endOfTrialPeriod ) ) {
				return member.books.size() < 2;
			}
			else {
				return member.books.size() > 1;
			}
		}
	}
}