package com.blogspot.that_java_guy;

import java.util.Arrays;
import java.util.List;

/**
 * @author Marko Bekhta
 */
public class Book {
	private final String title;
	private final String author;
	private final int numberOfPages;

	public Book(String title, String author, int numberOfPages) {
		this.title = title;
		this.author = author;
		this.numberOfPages = numberOfPages;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getNumberOfPages() {
		return this.numberOfPages;
	}

	public static List<Book> getListOfBooks() {
		return Arrays.asList(
				new Book( "Understanding Bean Validation 2.0: Bean Validation", "Antonio Goncalves ", 115 ),
				new Book( "High-Performance Java Persistence", "Vlad Mihalcea", 486 ),
				new Book( "Effective Java", "Joshua Bloch", 416 )
		);
	}
}
