package com.blogspot.that_java_guy;

import java.util.Arrays;

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
			return attribute.getId();
		}

		@Override
		public PhoneType convertToEntityAttribute(Integer dbData) {
			if ( dbData == null ) {
				return null;
			}
			return Arrays.stream( PhoneType.values() )
					.filter( item -> item.getId().equals( dbData ) )
					.findFirst().orElseThrow( IllegalArgumentException::new );
		}
	}

	/**
	 * Find an enum value by it's {@code id} in {@code values} array.
	 *
	 * @param id an {@code id} of the enum that we search for
	 * @param values an array of values to search
	 * @param <ID> an id type for the enum
	 * @param <T> an actual enum type
	 * @return if found - enum constant value, otherwise throws an {@link IllegalArgumentException}
	 */
	public static <ID, T extends DbEnumConstant<ID>> T findByID(ID id, T[] values) {
		return Arrays.stream( values )
				.filter( item -> item.getId().equals( id ) )
				.findFirst().orElseThrow( IllegalArgumentException::new );
	}

}
