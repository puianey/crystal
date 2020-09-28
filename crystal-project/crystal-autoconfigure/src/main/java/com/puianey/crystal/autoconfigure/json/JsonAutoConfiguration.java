package com.puianey.crystal.autoconfigure.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.puianey.crystal.auto.annotation.AutoConfiguration;
import com.puianey.crystal.tools.json.serializer.BigDecimalSerializer;
import com.puianey.crystal.tools.json.serializer.SensitiveSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import static com.puianey.crystal.autoconfigure.json.JsonProperties.JSON_PREFIX;

/**
 * @Author: puianey
 * @Date: 2019-06-04 18:46
 * @Description:
 */
@AutoConfiguration
@Configuration
@ConditionalOnClass({ObjectMapper.class, BigDecimalSerializer.class})
public class JsonAutoConfiguration {

	@Configuration
	@EnableConfigurationProperties(JsonProperties.class)
	@ConditionalOnClass({LocalDateTimeSerializer.class, Jackson2ObjectMapperBuilder.class})
	protected static class JsonDateTimeConfiguration {

		private final JsonProperties jsonProperties;

		public JsonDateTimeConfiguration(JsonProperties jsonProperties) {
			this.jsonProperties = jsonProperties;
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalDateSerializer localDateSerializer() {
			return new LocalDateSerializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalDateFormat()));
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalTimeSerializer localTimeSerializer() {
			return new LocalTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalTimeFormat()));
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalDateTimeSerializer localDateTimeSerializer() {
			return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalDateTimeFormat()));
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalDateDeserializer localDateDeserializer() {
			return new LocalDateDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalDateFormat()));
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalTimeDeserializer localTimeDeserializer() {
			return new LocalTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalTimeFormat()));
		}

		@Bean
		@ConditionalOnMissingBean
		public LocalDateTimeDeserializer localDateTimeDeserializer() {
			return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(jsonProperties.getDateTime().getLocalDateTimeFormat()));
		}

		@Bean
		@ConditionalOnBean({
			LocalDateSerializer.class,
			LocalTimeSerializer.class,
			LocalDateTimeSerializer.class,
			LocalDateDeserializer.class,
			LocalTimeDeserializer.class,
			LocalDateTimeDeserializer.class
		})
		public Jackson2ObjectMapperBuilderCustomizer DateTimeBuilderCustomizer() {
			return builder -> builder
				.locale(Locale.CHINA)
				.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
				.serializerByType(LocalDate.class, localDateSerializer())
				.serializerByType(LocalTime.class, localTimeSerializer())
				.serializerByType(LocalDateTime.class, localDateTimeSerializer())
				.deserializerByType(LocalDate.class, localDateDeserializer())
				.deserializerByType(LocalTime.class, localTimeDeserializer())
				.deserializerByType(LocalDateTime.class, localDateTimeDeserializer())
				.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
				.featuresToDisable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
				.featuresToDisable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
				.serializationInclusion(JsonInclude.Include.NON_NULL);
		}

	}

	@Configuration
	@ConditionalOnClass(BigDecimalSerializer.class)
	protected static class JsonBigDecimalConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public BigDecimalSerializer bigDecimalSerializer() {
			return new BigDecimalSerializer();
		}

		@Bean
		@ConditionalOnBean(BigDecimalSerializer.class)
		public Jackson2ObjectMapperBuilderCustomizer BigDecimalBuilderCustomizer(BigDecimalSerializer bigDecimalSerializer) {
			return builder -> builder.serializerByType(BigDecimal.class, bigDecimalSerializer);
		}

	}

	@Configuration
	@ConditionalOnClass(SensitiveSerializer.class)
	@ConditionalOnProperty(prefix = JSON_PREFIX, name = "sensitive.enable", havingValue = "true")
	protected static class JsonSensitiveConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public SensitiveSerializer sensitiveSerializer() {
			return new SensitiveSerializer();
		}

		@Bean
		@ConditionalOnBean(SensitiveSerializer.class)
		public Jackson2ObjectMapperBuilderCustomizer SensitiveBuilderCustomizer() {
			return builder -> builder.serializerByType(String.class, sensitiveSerializer());
		}

	}

}

