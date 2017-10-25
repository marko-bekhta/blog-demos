package com.blogspot.that_java_guy.converters;

import com.blogspot.that_java_guy.PhoneType;
import com.blogspot.that_java_guy.util.DbEnumConstantHelper;

/**
 * @author Marko Bekhta
 */
public class PhoneTypeAttributeConverter extends AbstractAttributeConverter<PhoneType, Integer> {
	@Override
	protected DbEnumConstantHelper<PhoneType, Integer> helper() {
		return PhoneType.HELPER;
	}
}
