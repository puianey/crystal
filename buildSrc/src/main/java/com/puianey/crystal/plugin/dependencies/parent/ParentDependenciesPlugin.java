package com.puianey.crystal.plugin.dependencies.parent;

import com.puianey.crystal.plugin.dependencies.optional.OptionalDependenciesPlugin;

import java.util.Collections;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.Dependency;

public class ParentDependenciesPlugin implements Plugin<Project> {

	public void apply(Project project) {
		ConfigurationContainer configurations = project.getConfigurations();
		Configuration dependencyManagement = (Configuration) configurations.create("dependencyManagement", (configuration) -> {
			configuration.setVisible(false);
			configuration.setCanBeConsumed(false);
			configuration.setCanBeResolved(false);
		});
		configurations.matching((configuration) -> {
			return configuration.getName().endsWith("Classpath") || "annotationProcessor".equals(configuration.getName()) || "testAnnotationProcessor".equals(configuration.getName());
		}).all((configuration) -> {
			configuration.extendsFrom(new Configuration[]{dependencyManagement});
		});
		Dependency frameworkParent = project.getDependencies().platform(project.getDependencies().project(Collections.singletonMap("path", ":crystal-project:crystal-parent")));
		dependencyManagement.getDependencies().add(frameworkParent);
		project.getPlugins().withType(OptionalDependenciesPlugin.class, (optionalDependencies) -> {
			configurations.getByName("optional").extendsFrom(new Configuration[]{dependencyManagement});
		});
	}
}

