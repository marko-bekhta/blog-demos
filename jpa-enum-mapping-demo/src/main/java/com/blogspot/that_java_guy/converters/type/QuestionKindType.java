package com.blogspot.that_java_guy.converters.type;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import com.blogspot.that_java_guy.constants.QuestionKind;
import com.blogspot.that_java_guy.converters.type.descriptor.QuestionKindJavaTypeDescriptor;

/**
 * @author Marko Bekhta
 */
public class QuestionKindType extends AbstractSingleColumnStandardBasicType<QuestionKind> {

	public static final QuestionKindType INSTANCE = new QuestionKindType();

	public QuestionKindType() {
		super( VarcharTypeDescriptor.INSTANCE, QuestionKindJavaTypeDescriptor.INSTANCE );
	}

	@Override
	public String getName() {
		return "questionKind";
	}

	@Override
	protected boolean registerUnderJavaType() {
		return true;
	}
}
