package com.blogspot.that_java_guy;

import javax.persistence.AttributeConverter;

import com.blogspot.that_java_guy.util.DbEnumConstant;
import com.blogspot.that_java_guy.util.DbEnumConstantHelper;

/**
 * @author Marko Bekhta
 */
public enum PhoneType implements DbEnumConstant<Integer> {


	HOME( 1, "home" ), CELL( 2, "cell" ), WORK( 3, " work" ),;

	public static final DbEnumConstantHelper<PhoneType, Integer> HELPER = new DbEnumConstantHelper<>( PhoneType.class );

	private Integer id;
	private String name;

	PhoneType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public static class PhoneTypeAttributeConverter implements AttributeConverter<PhoneType, Integer> {
		@Override
		public Integer convertToDatabaseColumn(PhoneType attribute) {
			return null;
		}

		@Override
		public PhoneType convertToEntityAttribute(Integer dbData) {
			return null;
		}
	}

}
