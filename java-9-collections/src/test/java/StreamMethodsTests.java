import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marko Bekhta
 */
public class StreamMethodsTests {

	@Test
	public void newStreamsCollectorsSimpleCase() throws Exception {

		// Java 8:
		List<Student> students = getGroupStream()
				.flatMap( gr -> gr.getStudents().stream() )
				.filter( student -> student.getAverageMark() > 10L )
				.collect( Collectors.toList() );

		assertThat( students ).hasSize( 3 );

		// new Java 9 Collectors#filtering collector:
		students = getGroupStream()
				.flatMap( gr -> gr.getStudents().stream() )
				.collect( Collectors.filtering( student -> student.getAverageMark() > 10L, Collectors.toList() ) );

		assertThat( students ).hasSize( 3 );

		// Java 9 collecting right away without other operations:
		students = getGroupStream()
				.collect(
						Collectors.flatMapping(
								gr -> gr.getStudents().stream(),
								Collectors.filtering(
										student -> student.getAverageMark() > 10L,
										Collectors.toList()
								)
						)
				);

		assertThat( students ).hasSize( 3 );
	}

	@Test
	public void newCollectorsForPartitioning() throws Exception {
		Map<Boolean, List<Student>> map = getGroupStream()
				.collect(
						Collectors.partitioningBy(
								group -> group.getStudents().size() > 2,
								Collectors.flatMapping( group -> group.getStudents().stream(), Collectors.toList() )
						)
				);
		assertThat( map ).hasSize( 2 );
		assertThat( map.get( Boolean.FALSE ) ).hasSize( 2 );
		assertThat( map.get( Boolean.TRUE ) ).hasSize( 3 );

		map = getGroupStream()
				.collect(
						Collectors.partitioningBy(
								group -> group.getStudents().size() > 2,
								Collectors.collectingAndThen(
										Collectors.toList(),
										list -> list.stream().flatMap( g -> g.getStudents().stream() )
												.collect( Collectors.toList() )
								)
						)
				);
		assertThat( map ).hasSize( 2 );
		assertThat( map.get( Boolean.FALSE ) ).hasSize( 2 );
		assertThat( map.get( Boolean.TRUE ) ).hasSize( 3 );
	}

	@Test
	public void newCollectorsForGrouping() throws Exception {
		// Java 9 new filtering and flatMapping collectors
		Map<Group, List<Student>> map = getGroupStream()
				.collect(
						Collectors.groupingBy(
								Function.identity(),
								Collectors.flatMapping(
										g -> g.getStudents().stream(),
										Collectors.filtering(
												student -> student.getAverageMark() > 5,
												Collectors.toList()
										)
								)
						)
				);

		//previous collectors
		map = getGroupStream()
				.collect(
						Collectors.groupingBy(
								Function.identity(),
								Collectors.collectingAndThen(
										Collectors.toList(),
										list -> list.stream().flatMap( g -> g.getStudents().stream() )
												.filter( student -> student.getAverageMark() > 5 )
												.collect( Collectors.toList() )
								)
						)
				);

	}

	private static Stream<Group> getGroupStream() {
		Group group = new Group( "group 1" );
		group.addStudent( new Student( "John", "Doe", 10L ) );
		group.addStudent( new Student( "Jane", "Doe", 15L ) );

		Group anotherGroup = new Group( "group 2" );
		anotherGroup.addStudent( new Student( "Ellie", "Smith", 45L ) );
		anotherGroup.addStudent( new Student( "Alex", "Brown", 45L ) );
		anotherGroup.addStudent( new Student( "Max", "Low", 1L ) );

		return Stream.of( group, anotherGroup );
	}


	@Test
	public void dropTakeExample() throws Exception {
		AtomicInteger num = new AtomicInteger( 1 );

		//creates an "infinite" stream
		List<Integer> integers = IntStream.generate( num::incrementAndGet )
				//skip elements until the element < 100
				.dropWhile( i -> i < 100 )
				// process elements till the element < 2000
				.takeWhile( i -> i < 2000 )
				//just a simple filter operation
				.filter( i -> i % 5 == 0 )
				.boxed().collect( Collectors.toList() );
		assertThat( integers ).isNotEmpty();
	}

	@Test
	public void iterateExample() throws Exception {
		Integer num = 0;
		Stream.iterate( num, i -> i < 10, i -> i = i + 1 )
				.forEach( System.out::println );
	}

	@Test
	public void ofNullable() throws Exception {
		// if a null object is passed you will receive an empty stream
		assertThat( Stream.ofNullable( null ).count() ).isEqualTo( 0L );
		// otherwise you would get a stream of that one element
		assertThat( Stream.ofNullable( "" ).count() ).isEqualTo( 1L );
	}

}
