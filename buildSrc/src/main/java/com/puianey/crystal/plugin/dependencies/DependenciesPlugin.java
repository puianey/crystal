package com.puianey.crystal.plugin.dependencies;

import com.puianey.crystal.plugin.dependencies.optional.OptionalDependenciesPlugin;
import com.puianey.crystal.plugin.dependencies.parent.ParentDependenciesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DependenciesPlugin implements Plugin<Project> {

	public void apply(Project project) {
		project.getPlugins().apply(ParentDependenciesPlugin.class);
		project.getPlugins().apply(OptionalDependenciesPlugin.class);
	}
}
