package com.blogspot.that_java_guy;

import java.time.LocalDate;
import java.util.Collections;
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
public class LibraryMemberAdvancedCustomConstraintTest extends AbstractValidationTest {

	@Test
	public void testValidation() {
		OtherLibraryMember member = new OtherLibraryMember(
				"John Doe",
				LocalDate.now().plusDays( 10 ),
				Book.getListOfBooks()
		);

		Set<ConstraintViolation<OtherLibraryMember>> violations = validator.validate( member );

		// check there's only one violation
		assertThat( violations ).hasSize( 1 );
		ConstraintViolation<OtherLibraryMember> violation = violations.iterator().next();
		// check that it's a violation of ScriptAssert and that message is correct
		assertThat( violation.getConstraintDescriptor().getAnnotation().annotationType() )
				.isEqualTo( ValidLibraryMember.class );
		assertThat( violation.getMessage() )
				.isEqualTo( "Member is still on trial period and cannot have more than 1 book. But there are 3 books." );
	}

	@Test
	public void testValidation2() {
		OtherLibraryMember member = new OtherLibraryMember(
				"John Doe",
				LocalDate.now().minusDays( 10 ),
				Collections.emptyList()
		);

		Set<ConstraintViolation<OtherLibraryMember>> violations = validator.validate( member );

		// check there's only one violation
		assertThat( violations ).hasSize( 1 );
		ConstraintViolation<OtherLibraryMember> violation = violations.iterator().next();
		// check that it's a violation of ScriptAssert and that message is correct
		assertThat( violation.getConstraintDescriptor().getAnnotation().annotationType() )
				.isEqualTo( ValidLibraryMember.class );
		assertThat( violation.getMessage() )
				.isEqualTo(
						"Member is a regular library visitor and should have more than 1 book. But there is just 0 books." );
	}

	@ValidLibraryMember
	public static class OtherLibraryMember {
		public final String name;
		public final LocalDate endOfTrialPeriod;
		public final List<Book> books;

		public OtherLibraryMember(String name, LocalDate endOfTrialPeriod, List<Book> books) {
			this.name = name;
			this.endOfTrialPeriod = endOfTrialPeriod;
			this.books = books;
		}
	}

	public static class ValidLibraryMemberValidator
			implements ConstraintValidator<ValidLibraryMember, OtherLibraryMember> {

		@Override
		public boolean isValid(OtherLibraryMember member, ConstraintValidatorContext context) {
			// null values are considered valid.
			if ( member == null ) {
				return true;
			}

			String messagePattern = null;
			if ( LocalDate.now().isBefore( member.endOfTrialPeriod ) ) {
				if ( member.books.size() > 2 ) {
					messagePattern = "Member is still on trial period and cannot have more " +
							"than 1 book. But there are %d books.";
				}
			}
			else {
				if ( member.books.size() < 1 ) {
					messagePattern = "Member is a regular library visitor and should have more " +
							"than 1 book. But there is just %d books.";
				}
			}
			if ( messagePattern != null ) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						String.format(
								messagePattern,
								member.books.size()
						)
				).addConstraintViolation();
				return false;
			}
			return true;
		}
	}
}