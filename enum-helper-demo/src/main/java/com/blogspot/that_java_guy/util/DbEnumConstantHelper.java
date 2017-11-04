package com.blogspot.that_java_guy.util;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * {@link Class#enumConstants}
 *
 * @author Marko Bekhta
 */
public class DbEnumConstantHelper<ActualEnum extends DbEnumConstant<ID>, ID> {

	private final Class<ActualEnum> type;
	private final ActualEnum[] enumConstants;

	public DbEnumConstantHelper(Class<ActualEnum> type) {
		this.type = type;
		this.enumConstants = type.getEnumConstants();
	}

	/**
	 * Finds enum {@link ActualEnum} by id using its {@link DbEnumConstant#getId()}
	 * method.
	 *
	 * @param id an id to search by
	 * @return {@link ActualEnum} enum constant if found. Could throw
	 * {@link NoSuchElementException}
	 * if there's no constant for given {@code id}
	 */
	public ActualEnum byId(ID id) {
		return byFilter( elem -> elem.getId().equals( id ) );
	}

	/**
	 * Finds enum {@link ActualEnum} by id using its {@link DbEnumConstant#getId()}
	 * method. If one is not found - returns a default value.
	 *
	 * @param id an id to search by
	 * @param defaultValue a default value to return if nothing is found for given
	 * {@code id}
	 * @return {@link ActualEnum} enum constant if found, {@code defaultValue}
	 * otherwise
	 */
	public ActualEnum byIdOrDefault(ID id, ActualEnum defaultValue) {
		return byFilterOrDefault( elem -> elem.getId().equals( id ), defaultValue );
	}

	/**
	 * Finds enum {@link ActualEnum} by id using its {@link DbEnumConstant#getId()}
	 * method. If one is not found - will throw an exception provided by the passed
	 * {@code exceptionSupplier}.
	 *
	 * @param id an id to search by
	 * @param exceptionSupplier provides an exception that will be thrown in case
	 * nothing is found for a given {@code id}
	 * @param <X> an exception type to be thrown in case nothing is found
	 * @return {@link ActualEnum} enum constant if found, otherwise throws an
	 * exception provided by {@code exceptionSupplier}
	 *
	 * @throws X when nothing is found for a given {@code id}
	 */
	public <X extends Throwable> ActualEnum byIdOrThrow(
			ID id,
			Supplier<? extends X> exceptionSupplier) throws X {
		return byFilterOrThrow( elem -> elem.getId().equals( id ), exceptionSupplier );
	}

	/**
	 * Finds enum {@link ActualEnum} by name using its {@link DbEnumConstant#getName()}
	 * method.
	 *
	 * @param name a name to search by
	 * @return {@link ActualEnum} enum constant if found. Could throw
	 * {@link NoSuchElementException} if there's no constant for given {@code name}
	 */
	public ActualEnum byName(String name) {
		return byFilter( elem -> elem.getName().equals( name ) );
	}

	/**
	 * Finds enum {@link ActualEnum} by name using its {@link DbEnumConstant#getName()}
	 * method. If one is not found - returns a default value.
	 *
	 * @param name a name to search by
	 * @param defaultValue a default value to return if nothing is found for given
	 * {@code name}
	 * @return {@link ActualEnum} enum constant if found, {@code defaultValue}
	 * otherwise
	 */
	public ActualEnum byNameOrDefault(String name, ActualEnum defaultValue) {
		return byFilterOrDefault( elem -> elem.getName().equals( name ), defaultValue );
	}

	/**
	 * Finds enum {@link ActualEnum} by name using its {@link DbEnumConstant#getName()}
	 * method. If one is not found - will throw an exception provided by the passed
	 * {@code exceptionSupplier}.
	 *
	 * @param name an name to search by
	 * @param exceptionSupplier provides an exception that will be thrown in case
	 * nothing is found for a given {@code name}
	 * @param <X> an exception type to be thrown in case nothing is found
	 * @return {@link ActualEnum} enum constant if found, otherwise throws an exception
	 * provided by {@code exceptionSupplier}
	 *
	 * @throws X when nothing is found for a given {@code name}
	 */
	public <X extends Throwable> ActualEnum byNameOrThrow(String name, Supplier<? extends X> exceptionSupplier)
			throws X {
		return byFilterOrThrow( elem -> elem.getName().equals( name ), exceptionSupplier );
	}


	private ActualEnum byFilter(Predicate<ActualEnum> predicate) {
		return Arrays.stream( enumConstants )
				.filter( predicate )
				.findAny()
				.get();
	}

	private ActualEnum byFilterOrDefault(Predicate<ActualEnum> predicate, ActualEnum defaultValue) {
		return Arrays.stream( enumConstants )
				.filter( predicate )
				.findAny()
				.orElse( defaultValue );
	}

	private <X extends Throwable> ActualEnum byFilterOrThrow(
			Predicate<ActualEnum> predicate,
			Supplier<? extends X> exceptionSupplier) throws X {
		return Arrays.stream( enumConstants )
				.filter( predicate )
				.findAny()
				.orElseThrow( exceptionSupplier );
	}
}
