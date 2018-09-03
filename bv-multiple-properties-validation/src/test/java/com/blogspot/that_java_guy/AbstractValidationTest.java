package com.blogspot.that_java_guy;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;

/**
 * @author Marko Bekhta
 */
public class AbstractValidationTest {

	protected static Validator validator;

	@BeforeClass
	public static void setUp() throws Exception {
		validator = Validation
				.buildDefaultValidatorFactory()
				.getValidator();
	}
}
