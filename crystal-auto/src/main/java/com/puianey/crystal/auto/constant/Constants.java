package com.puianey.crystal.auto.constant;

import com.puianey.crystal.auto.annotation.AutoConfiguration;
import com.puianey.crystal.auto.annotation.AutoContextInitializer;
import com.puianey.crystal.auto.annotation.AutoListener;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

public interface Constants {

	@Getter
	@AllArgsConstructor
	enum AutoType {

		AUTO_CONFIGURATION(AutoConfiguration.class.getCanonicalName(), "org.springframework.boot.autoconfigure.EnableAutoConfiguration"),

		AUTO_CONTEXT_INITIALIZER(AutoContextInitializer.class.getCanonicalName(), "org.springframework.context.ApplicationContextInitializer"),

		AUTO_LISTENER(AutoListener.class.getCanonicalName(), "org.springframework.context.ApplicationListener");

		String annotation;

		String configureKey;

		public static String fromAnnotation(String annotation) {
			return Arrays.stream(AutoType.values())
				.filter(autoType -> Objects.equals(autoType.getAnnotation(), annotation))
				.map(AutoType::getConfigureKey)
				.findFirst()
				.orElse(null);
		}

	}

}
