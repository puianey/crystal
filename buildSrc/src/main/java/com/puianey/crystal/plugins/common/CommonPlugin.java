package com.puianey.crystal.plugins.common;

import com.puianey.crystal.plugins.dependencies.DependenciesPlugin;
import com.puianey.crystal.plugins.lombok.LombokPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CommonPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(DependenciesPlugin.class);
		plugins.apply(LombokPlugin.class);
	}

}
