package com.puianey.crystal.auto.processing.handlers;

import com.google.auto.service.AutoService;
import com.google.common.collect.Maps;
import com.puianey.crystal.auto.annotation.Tag;
import com.puianey.crystal.auto.constant.Constants;
import com.puianey.crystal.auto.util.FactoriesFiles;
import lombok.SneakyThrows;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Tag({
	Constants.AutoType.AUTO_CONFIGURATION,
	Constants.AutoType.AUTO_CONTEXT_INITIALIZER,
	Constants.AutoType.AUTO_LISTENER
})
@AutoService(AnnotationHandler.class)
public class AutoFactoriesHandler extends AnnotationHandler {

	private final Map<String, Set<String>> factories = Maps.newHashMap();

	private static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

	@Override
	public void preHandle(Set<? extends Element> elements, TypeElement typeElement) {

		factories.merge(
			Constants.AutoType.fromAnnotation(typeElement.getQualifiedName().toString()),
			elements.stream().map(Object::toString).collect(Collectors.toSet()),
			(oldValue, newValue) -> {
				oldValue.addAll(newValue);
				return oldValue;
			});

	}

	@Override
	@SneakyThrows
	public void doHandle() {
		if (factories.isEmpty()) {
			return;
		}
		// spring.factories
		var factoriesFile = filer.createResource(StandardLocation.CLASS_OUTPUT, "", FACTORIES_RESOURCE_LOCATION);
		FactoriesFiles.writeFactoriesFile(factories, factoriesFile.openOutputStream());
	}

}

