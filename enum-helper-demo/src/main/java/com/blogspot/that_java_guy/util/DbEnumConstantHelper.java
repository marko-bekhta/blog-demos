package com.blogspot.that_java_guy.util;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Marko Bekhta
 */
public class DbEnumConstantHelper<ActualEnum extends DbEnumConstant<ID>, ID> {

	private final Class<ActualEnum> type;
	private final ActualEnum[] enumConstants;

	public DbEnumConstantHelper(Class<ActualEnum> type) {
		this.type = type;
		this.enumConstants = type.getEnumConstants();
	}

	public ActualEnum byId(ID id) {
		return byFilter( elem -> elem.getId().equals( id ) );
	}

	public ActualEnum byIdOrDefault(ID id, ActualEnum defaultValue) {
		return byFilterOrDefault( elem -> elem.getId().equals( id ), defaultValue );
	}

	public <X extends Throwable> ActualEnum byIdOrThrow(ID id, Supplier<? extends X> exceptionSupplier) throws X {
		return byFilterOrThrow( elem -> elem.getId().equals( id ), exceptionSupplier );
	}

	public ActualEnum byName(String name) {
		return byFilter( elem -> elem.getName().equals( name ) );
	}

	public ActualEnum byNameOrDefault(String name, ActualEnum defaultValue) {
		return byFilterOrDefault( elem -> elem.getName().equals( name ), defaultValue );
	}

	public <X extends Throwable> ActualEnum byNameOrThrow(String name, Supplier<? extends X> exceptionSupplier) throws X {
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
