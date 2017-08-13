package com.blogspot.that_java_guy.constants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class QuestionKindTest {

	@Test
	public void byId() {
		for ( QuestionKind kind : QuestionKind.values() ) {
			assertEquals( kind, QuestionKind.byId( kind.getId() ) );
		}

		assertNull( QuestionKind.byId( -1 ) );
	}

}
