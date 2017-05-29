package com.blogspot.that_java_guy.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Marko Bekhta
 */
class GradeTest {

	@Test
	void byChar() {
		for ( Grade grade : Grade.values() ) {
			Assertions.assertEquals( grade, Grade.byChar( grade.charGrade ) );
		}

		Assertions.assertNull( Grade.byChar( 'P' ) );
	}

}
