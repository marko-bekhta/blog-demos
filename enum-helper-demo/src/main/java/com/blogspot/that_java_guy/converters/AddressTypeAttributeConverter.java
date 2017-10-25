package com.blogspot.that_java_guy.converters;

import com.blogspot.that_java_guy.AddressType;
import com.blogspot.that_java_guy.util.DbEnumConstantHelper;

/**
 * @author Marko Bekhta
 */
public class AddressTypeAttributeConverter extends AbstractAttributeConverter<AddressType, Long> {
	@Override
	protected DbEnumConstantHelper<AddressType, Long> helper() {
		return AddressType.HELPER;
	}
}
