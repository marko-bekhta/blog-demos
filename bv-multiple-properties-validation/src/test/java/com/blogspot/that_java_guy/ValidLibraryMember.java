package com.blogspot.that_java_guy;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Marko Bekhta
 */
@Documented
@Constraint(validatedBy = {})
@Target({ TYPE })
@Retention(RUNTIME)
public @interface ValidLibraryMember {

	String message() default "Member has incorrect amount of books taken from library";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
