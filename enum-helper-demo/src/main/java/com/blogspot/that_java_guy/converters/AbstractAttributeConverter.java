package com.blogspot.that_java_guy.converters;

import javax.persistence.AttributeConverter;

import com.blogspot.that_java_guy.util.DbEnumConstant;
import com.blogspot.that_java_guy.util.DbEnumConstantHelper;

/**
 * @author Marko Bekhta
 */
public abstract class AbstractAttributeConverter<ActualEnum extends DbEnumConstant<ID>, ID>
		implements AttributeConverter<ActualEnum, ID> {

	protected abstract DbEnumConstantHelper<ActualEnum, ID> helper();

	@Override

	public ID convertToDatabaseColumn(ActualEnum attribute) {
		return attribute.getId();
	}

	@Override
	public ActualEnum convertToEntityAttribute(ID dbData) {
		// if the value in the DB is null we will return null as well
		if ( dbData == null ) {
			return null;
		}
		// Otherwise, a helper is used to find a matching enum constant,
		// and if one cannot be found an exception is thrown.
		return helper().byIdOrThrow(
				dbData,
				() -> new IllegalStateException(
						"There is no corresponding enum for the given value from the database" )
		);
	}
}
