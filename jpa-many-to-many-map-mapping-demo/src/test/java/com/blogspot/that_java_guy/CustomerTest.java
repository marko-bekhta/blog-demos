package com.blogspot.that_java_guy;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class CustomerTest extends AbstractEntityTest {
	@Test
	public void rentFilm() throws Exception {
		Customer customer = Customer.with( Name.from( "Charley", "Brown" ) );

		// Film with id = 9 is prepopulated in the test data
		Film film = entityManager.find( Film.class, 9 );
		customer.rentFilm( film );

		entityManager.persist( customer );
	}
}
