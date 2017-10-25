package com.blogspot.that_java_guy;

import java.time.Duration;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class FilmTest extends AbstractEntityTest {
	@Test
	public void simpleFilmCreatedTest() throws Exception {
		Actor actor1 = Actor.with( Name.from( "John", "Doe" ) );
		Actor actor2 = Actor.with( Name.from( "Jane", "Doe" ) );

		// need to persist actors separately as operation will not
		// be cascaded
		entityManager.persist( actor1 );
		entityManager.persist( actor2 );

		Film film = Film.Builder.instance()
				.title( "Best film ever" )
				.lenth( Duration.ofHours( 1 ).plusMinutes( 20 ) )
				.create();

		film.addCharacter(
				actor1,
				CharacterInformation.Builder.instance()
						.withName( Name.from( "John", "Smith" ) )
						.isMainCharacter( true )
						.create()
		);

		film.addCharacter(
				actor2,
				CharacterInformation.Builder.instance()
						.withName( Name.from( "Jane", "Smith" ) )
						.isMainCharacter( true )
						.create()
		);
		entityManager.persist( film );
	}
}
