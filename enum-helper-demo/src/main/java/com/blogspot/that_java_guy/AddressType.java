package com.blogspot.that_java_guy;

import com.blogspot.that_java_guy.util.DbEnumConstant;
import com.blogspot.that_java_guy.util.DbEnumConstantHelper;

/**
 * @author Marko Bekhta
 */
public enum AddressType implements DbEnumConstant<Long> {

	HOME( 1L, "home address" ), WORK( 2L, "work address" ),;

	public static final DbEnumConstantHelper<AddressType, Long> HELPER = new DbEnumConstantHelper<>( AddressType.class );

	private Long id;
	private String name;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	AddressType(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
