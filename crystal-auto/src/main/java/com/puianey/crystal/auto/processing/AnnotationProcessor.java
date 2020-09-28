package com.puianey.crystal.auto.processing;

import com.google.auto.service.AutoService;
import com.puianey.crystal.auto.HandlerLibrary;
import com.puianey.crystal.auto.constant.Constants;
import com.puianey.crystal.auto.processing.handlers.AnnotationHandler;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

	private HandlerLibrary handlerLibrary;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		this.handlerLibrary = HandlerLibrary.load(processingEnv);
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		if (roundEnv.processingOver()) {
			handlerLibrary.getAllAnnotationHandlers().forEach(AnnotationHandler::doHandle);
			return true;
		}
		annotations.forEach(annotation ->
			Optional.ofNullable(roundEnv.getElementsAnnotatedWith(annotation))
				.filter(elements -> !elements.isEmpty())
				.ifPresent(elements -> {
					Optional.ofNullable(annotation.getQualifiedName())
						.map(CharSequence::toString)
						.map(qualifiedName -> handlerLibrary.getAnnotationHandler(qualifiedName))
						.ifPresent(handlers -> {
							handlers.forEach(handler -> {
								handler.preHandle(elements, annotation);
							});
						});
				})
		);
		return true;

	}

	@Override
	public Set<String> getSupportedAnnotationTypes() {
		return Arrays.stream(Constants.AutoType.values())
			.map(Constants.AutoType::getAnnotation)
			.collect(Collectors.toSet());
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}

}
