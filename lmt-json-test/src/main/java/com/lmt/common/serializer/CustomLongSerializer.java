package com.lmt.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * @author ducx
 * @date 2017-06-19
 *
 */
public class CustomLongSerializer extends JsonSerializer<Long> {

	@Override
	public void serialize(Long value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException {
		gen.writeString(String.valueOf(value));
	}
	
	@Override
	public Class<Long> handledType() {
		return Long.class;
	}

}
