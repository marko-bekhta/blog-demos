package com.blogspot.that_java_guy.immutables.model;

import org.immutables.value.Value;

/**
 * @author Marko Bekhta
 */

@Value.Immutable
public interface Book {

	ISBN getISBN();

	String getTitle();

	Author getAuthor();

}
