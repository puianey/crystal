package com.puianey.crystal.plugins.dependencies.parent;

import com.puianey.crystal.plugins.dependencies.optional.OptionalDependenciesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;
import java.util.Objects;

import static com.puianey.crystal.plugins.dependencies.optional.OptionalDependenciesPlugin.OPTIONAL_CONFIGURATION_NAME;
import static org.gradle.api.plugins.JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME;
import static org.gradle.api.plugins.JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME;

public class ParentDependenciesPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {

		var configurations = project.getConfigurations();
		var dependencyManagement = configurations.create("dependencyManagement", (configuration) -> {
			configuration.setVisible(false);
			configuration.setCanBeConsumed(false);
			configuration.setCanBeResolved(false);
		});
		configurations.matching(configuration ->
			configuration.getName().endsWith("Classpath")
				|| Objects.equals(configuration.getName(), ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
				|| Objects.equals(configuration.getName(), TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
		).all(configuration ->
			configuration.extendsFrom(dependencyManagement)
		);

		var frameworkParent = project.getDependencies()
			.platform(project.getDependencies()
				.project(Collections.singletonMap("path", ":crystal-project:crystal-parent")));
		dependencyManagement.getDependencies().add(frameworkParent);
		project.getPlugins().withType(OptionalDependenciesPlugin.class, optionalDependencies ->
			configurations.getByName(OPTIONAL_CONFIGURATION_NAME).extendsFrom(dependencyManagement)
		);

	}

}

