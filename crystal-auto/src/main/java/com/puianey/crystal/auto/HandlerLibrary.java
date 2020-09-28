package com.puianey.crystal.auto;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.puianey.crystal.auto.annotation.Tag;
import com.puianey.crystal.auto.constant.Constants;
import com.puianey.crystal.auto.processing.handlers.AnnotationHandler;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.*;

public class HandlerLibrary {

	private final Map<String, Set<AnnotationHandler>> annotationHandlers = Maps.newHashMapWithExpectedSize(Constants.AutoType.values().length);

	private final Set<AnnotationHandler> handlers = Sets.newHashSet();

	public static HandlerLibrary load(ProcessingEnvironment processingEnv) {
		var handlerLibrary = new HandlerLibrary();
		handlerLibrary.loadAnnotationHandlers(processingEnv);
		return handlerLibrary;
	}

	public Set<AnnotationHandler> getAnnotationHandler(String canonicalName) {
		return annotationHandlers.get(canonicalName);
	}

	public Set<AnnotationHandler> getAllAnnotationHandlers() {
		return handlers;
	}

	private void loadAnnotationHandlers(ProcessingEnvironment processingEnv) {

		var classLoader = this.getClass().getClassLoader();
		var loaders = ServiceLoader.load(AnnotationHandler.class, classLoader);
		loaders.forEach(annotationHandler -> {
			annotationHandler.setProcessingEnv(processingEnv);
			handlers.add(annotationHandler);
			Arrays.stream(
				annotationHandler.getClass()
					.getAnnotation(Tag.class)
					.value()
			).forEach(autoType -> {
				annotationHandlers.merge(
					autoType.getAnnotation(),
					Sets.newHashSet(annotationHandler),
					(oldValue, newValue) -> {
						oldValue.addAll(newValue);
						return oldValue;
					});
			});
		});

	}

}
