package com.blogspot.that_java_guy.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class GradeTest {

	@Test
	public void byChar() {
		for ( Grade grade : Grade.values() ) {
			assertEquals( grade, Grade.byChar( grade.charGrade ) );
		}

		assertNull( Grade.byChar( 'P' ) );
	}

}
