package com.blogspot.that_java_guy.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Marko Bekhta
 */
class QuestionKindTest {

	@Test
	void byId() {
		for ( QuestionKind kind : QuestionKind.values() ) {
			Assertions.assertEquals( kind, QuestionKind.byId( kind.getId() ) );
		}

		Assertions.assertNull( QuestionKind.byId( -1 ) );
	}

}
