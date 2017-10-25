package com.blogspot.that_java_guy;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Marko Bekhta
 */
@Entity
@Table(name = "rental")
public class Rental extends BaseEntity {

	@ManyToOne
	private Film film;

	@ManyToOne
	private Customer customer;

	private LocalDateTime date;

	protected Rental() {
	}

	Rental(Film film, Customer customer, LocalDateTime date) {
		this.film = film;
		this.customer = customer;
		this.date = date;
	}

	public Film getFilm() {
		return film;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public boolean isRentedBetween(LocalDateTime start, LocalDateTime end) {
		return date.isAfter( start ) && date.isBefore( end );
	}

	public static class Builder {
		private Film film;
		private Customer customer;
		private LocalDateTime date;

		public static Builder instance() {
			return new Builder();
		}

		public Builder film(Film film) {
			this.film = film;
			return this;
		}

		public Builder byCustomer(Customer customer) {
			this.customer = customer;
			return this;
		}

		public Builder on(LocalDateTime date) {
			this.date = date;
			return this;
		}

		public Rental rent() {
			return new Rental( film, customer, date );
		}
	}
}
