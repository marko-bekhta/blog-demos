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
		return helper().byIdOrThrow(
				dbData,
				() -> new IllegalStateException(
						"There is no corresponding enum for the given value from the database" )
		);
	}
}
