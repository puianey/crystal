package com.puianey.crystal.plugins.dependencies;

import com.puianey.crystal.plugins.dependencies.optional.OptionalDependenciesPlugin;
import com.puianey.crystal.plugins.dependencies.parent.ParentDependenciesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class DependenciesPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(OptionalDependenciesPlugin.class);
		plugins.apply(ParentDependenciesPlugin.class);
	}

}
