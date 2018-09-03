package com.blogspot.that_java_guy;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;

import org.hibernate.validator.constraints.ScriptAssert;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
public class LibraryMemberScriptAssertTest extends AbstractValidationTest {

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
				.isEqualTo( ScriptAssert.class );
		assertThat( violation.getMessage() )
				.isEqualTo( "Member has incorrect amount of books taken from library" );
	}

	@ScriptAssert(
			lang = "groovy",
			alias = "_",
			script = "java.time.LocalDate.now().isBefore(_.endOfTrialPeriod) ? _.books.size() < 2 : _.books.size() > 0",
			message = "Member has incorrect amount of books taken from library")
	static class LibraryMember {
		private final String name;
		private final LocalDate endOfTrialPeriod;
		private final List<Book> books;

		public LibraryMember(String name, LocalDate endOfTrialPeriod, List<Book> books) {
			this.name = name;
			this.endOfTrialPeriod = endOfTrialPeriod;
			this.books = books;
		}
	}
}