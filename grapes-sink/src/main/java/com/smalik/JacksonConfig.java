package com.smalik;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Configuration
public class JacksonConfig {

	private DateTimeFormatter jodaDateTimeFormatter = ISODateTimeFormat.dateTime();
	private DateTimeFormatter jodaDateTimeParser = ISODateTimeFormat.dateTimeParser();

	@Bean
	@ConditionalOnProperty(value="jackson.config.enabled")
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
				.failOnEmptyBeans(false)
				.failOnUnknownProperties(false)
				.serializationInclusion(JsonInclude.Include.NON_NULL);

		builder.serializers(new JsonSerializer<Date>() {
			@Override
			public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeString(value.toInstant().toString());
			}

			@Override
			public Class<Date> handledType() {
				return Date.class;
			}
		}, new JsonSerializer<LocalDateTime>() {

			@Override
			public Class<LocalDateTime> handledType() {
				return LocalDateTime.class;
			}

			@Override
			public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
				gen.writeString(jodaDateTimeFormatter.print(value) + "Z");
			}
		});

		builder.deserializers(new JsonDeserializer<Date>() {
			@Override
			public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				String dateString = p.getValueAsString();
				return Date.from(Instant.parse(dateString));
			}

			@Override
			public Class<?> handledType() {
				return Date.class;
			}
		}, new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
				String dateString = p.getValueAsString();
				return jodaDateTimeParser.parseDateTime(dateString).toLocalDateTime();
			}

			@Override
			public Class<?> handledType() {
				return LocalDateTime.class;
			}
		});

		return builder;
	}
}
