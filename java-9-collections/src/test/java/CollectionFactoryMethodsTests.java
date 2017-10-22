import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author Marko Bekhta
 */
public class CollectionFactoryMethodsTests {

	@Test
	public void java8CollectionCreation() throws Exception {
		Set<String> fruits = new HashSet<>();
		fruits.add( "banana" );
		fruits.add( "pineapple" );
		fruits.add( "kiwi" );

		fruits = Collections.unmodifiableSet( fruits );

		//or using a "initializer block" of anonymous class that extends your set:
		fruits = Collections.unmodifiableSet( new HashSet<String>() {
			{
				add( "banana" );
				add( "pineapple" );
				add( "kiwi" );
			}
		} );

		// or even more weirder construction of multiple collections to just get a Set
		fruits = Collections.unmodifiableSet( new HashSet<>( Arrays.asList( "banana", "pineapple", "kiwi" ) ) );

		//or using streams:
		fruits = Stream.of( "banana", "pineapple", "kiwi" )
				.collect(
						Collectors.collectingAndThen(
								Collectors.toSet(),
								Collections::unmodifiableSet
						)
				);

		fruits = Set.of( "banana", "pineapple", "kiwi" );
	}

	@Test
	public void setProperties() throws Exception {
		Set<String> fruits = Set.of( "banana", "pineapple", "kiwi" );

		// immutability properties:
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> fruits.add( "orange" ) );
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> fruits.remove( "kiwi" ) );
		assertThat( fruits ).containsOnly( "banana", "pineapple", "kiwi" );

		// null is disallowed:
		assertThatExceptionOfType( NullPointerException.class )
				.isThrownBy( () -> Set.of( null ) );
		//duplicates are disallowed:
		assertThatExceptionOfType( IllegalArgumentException.class )
				.isThrownBy( () -> Set.of( "apple", "apple" ) );
	}

	@Test
	public void listProperties() throws Exception {
		List<String> fruits = List.of( "banana", "pineapple", "kiwi" );

		// immutability properties:
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> fruits.add( "orange" ) );
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> fruits.remove( "kiwi" ) );
		assertThat( fruits ).containsOnly( "banana", "pineapple", "kiwi" );

		// null is disallowed:
		assertThatExceptionOfType( NullPointerException.class )
				.isThrownBy( () -> List.of( null ) );
		//guarantee of the order
		assertThat( fruits ).containsExactly( "banana", "pineapple", "kiwi" );
	}

	@Test
	public void mapCreation() throws Exception {
		Map<String, Integer> numberOfFruits = Map.of(
				"banana", 1,
				"pineapple", 2,
				"kiwi", 3
		);
		//or using entries:
		numberOfFruits = Map.ofEntries(
				Map.entry( "banana", 1 ),
				Map.entry( "pineapple", 2 ),
				Map.entry( "kiwi", 3 )
		);
	}

	@Test
	public void mapProperties() throws Exception {
		Map<String, Integer> numberOfFruits = Map.ofEntries(
				Map.entry( "banana", 1 ),
				Map.entry( "pineapple", 2 ),
				Map.entry( "kiwi", 3 )
		);

		// immutability properties:
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> numberOfFruits.put( "orange", 10 ) );
		assertThatExceptionOfType( UnsupportedOperationException.class )
				.isThrownBy( () -> numberOfFruits.remove( "kiwi" ) );
		assertThat( numberOfFruits ).containsOnlyKeys( "banana", "pineapple", "kiwi" );

		// null is disallowed:
		assertThatExceptionOfType( NullPointerException.class )
				.isThrownBy( () -> Map.of( null, 1 ) );
		assertThatExceptionOfType( NullPointerException.class )
				.isThrownBy( () -> Map.of( "test", null ) );
		//duplicates are disallowed:
		assertThatExceptionOfType( IllegalArgumentException.class )
				.isThrownBy( () -> Map.of( "apple", 1, "apple", 20 ) );
	}
}
