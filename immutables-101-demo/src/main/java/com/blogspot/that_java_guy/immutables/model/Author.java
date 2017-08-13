package com.blogspot.that_java_guy.immutables.model;

import org.immutables.value.Value;

/**
 * @author Marko Bekhta
 */

@Value.Immutable
public abstract class Author {

	public abstract String getName();

	public abstract String getSurname();

}
