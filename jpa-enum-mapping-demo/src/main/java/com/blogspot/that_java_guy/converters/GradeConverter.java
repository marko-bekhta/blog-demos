package com.blogspot.that_java_guy.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.blogspot.that_java_guy.constants.Grade;

/**
 * These kind of attribute converters are only since JPA 2.1.
 *
 * @author Marko Bekhta
 */

@Converter
public class GradeConverter implements AttributeConverter<Grade, Character> {

	@Override
	public Character convertToDatabaseColumn(Grade attribute) {
		return attribute.charGrade;
	}

	@Override
	public Grade convertToEntityAttribute(Character dbData) {
		return Grade.byChar( dbData );
	}
}
