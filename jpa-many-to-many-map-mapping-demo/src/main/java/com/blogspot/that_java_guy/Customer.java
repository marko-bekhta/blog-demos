package com.blogspot.that_java_guy;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Marko Bekhta
 */
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

	private Name name;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Rental> rentals = new HashSet<>();

//	@OneToMany
//	@JoinTable(name = "rental",
//			joinColumns = @JoinColumn(name ="customer_id"),
//			inverseJoinColumns = @JoinColumn(name = "film_id")
//	)
//	@MapKeyColumn(name = "date")
//	private Map<LocalDateTime, Film> rentalsByDateTime;

	protected Customer() {
	}

	private Customer(Name name) {
		this.name = name;
	}

	public static Customer with(Name name) {
		return new Customer( name );
	}

	/**
	 * Will rent a {@code film} at current date and time.
	 *
	 * @param film a film to rent
	 */
	public Customer rentFilm(Film film) {
		rentals.add(
				Rental.Builder.instance()
						.film( film )
						.on( LocalDateTime.now() )
						.byCustomer( this )
						.rent()
		);

		return this;
	}

	/**
	 * @param start a start date of a period
	 * @param end an end date of the period
	 *
	 * @return a collection of distinct films rented by current customer during a time period
	 * between {@code start} and {@code end} dates
	 */
	public Collection<Film> uniqueRentedFilms(LocalDateTime start, LocalDateTime end) {
		return rentals.stream()
				.filter( rental -> rental.isRentedBetween( start, end ) )
				.map( Rental::getFilm )
				.collect( Collectors.collectingAndThen( Collectors.toSet(), Collections::unmodifiableSet ) );
	}

	/**
	 * @return a collection of distinct films rented for the whole time
	 */
	public Collection<Film> uniqueRentedFilmsForAllTime() {
		return rentals.stream()
				.map( Rental::getFilm )
				.collect( Collectors.collectingAndThen( Collectors.toSet(), Collections::unmodifiableSet ) );
	}

	/**
	 * @return rental information represented by a map where rented {@link Film} is a key
	 * and a list of dates when the {@code film} was rented as a value.
	 */
	public Map<Film, List<LocalDateTime>> rentalInformation() {
		return rentals.stream()
				.collect(
						Collectors.collectingAndThen(
								Collectors.groupingBy(
										Rental::getFilm,
										Collectors.mapping(
												Rental::getDate,
												Collectors.collectingAndThen(
														Collectors.toList(),
														Collections::unmodifiableList
												)
										)
								),
								Collections::unmodifiableMap
						)
				);
	}

}
