package com.blogspot.that_java_guy.converters.type.descriptor;

import java.lang.reflect.Type;
import java.util.Map;

import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import com.blogspot.that_java_guy.constants.QuestionKind;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author Marko Bekhta
 */
public class QuestionKindJavaTypeDescriptor extends AbstractTypeDescriptor<QuestionKind> {

	private static GsonBuilder builder = new GsonBuilder()
			.registerTypeAdapter( QuestionKind.class, new CustomJsonSerializer() );

	public static final QuestionKindJavaTypeDescriptor INSTANCE = new QuestionKindJavaTypeDescriptor();

	protected QuestionKindJavaTypeDescriptor() {
		super( QuestionKind.class );
	}

	@Override
	public String toString(QuestionKind value) {
		return builder.create().toJson( value );
	}

	@Override
	public QuestionKind fromString(String string) {
		if ( string == null ) {
			return null;
		}
		Map parsedJson = builder.create().fromJson( string, Map.class );
		return QuestionKind.byId( (int) parsedJson.get( "id" ) );
	}

	@Override
	public <X> X unwrap(QuestionKind value, Class<X> type, WrapperOptions options) {
		return StringTypeDescriptor.INSTANCE.unwrap(
				toString( value ),
				type,
				options
		);
	}

	@Override
	public <X> QuestionKind wrap(X value, WrapperOptions options) {
		return fromString( StringTypeDescriptor.INSTANCE.wrap( value, options ) );
	}

	private static class CustomJsonSerializer implements JsonSerializer<QuestionKind> {

		@Override
		public JsonElement serialize(QuestionKind questionKind, Type type, JsonSerializationContext jsonSerializationContext) {
			JsonObject element = new JsonObject();
			element.addProperty( "id", questionKind.getId() );
			element.addProperty( "description", questionKind.getQuestionTypeDescription() );
			element.addProperty( "answerKind", questionKind.getAnswerKind().name() );
			element.addProperty( "multipleAnswers", questionKind.isMultipleAnswersPossible() );
			return element;
		}

	}
}
