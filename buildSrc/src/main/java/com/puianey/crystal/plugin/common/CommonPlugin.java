package com.puianey.crystal.plugin.common;

import com.puianey.crystal.plugin.dependencies.DependenciesPlugin;
import com.puianey.crystal.plugin.lombok.LombokPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;

public class CommonPlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(DependenciesPlugin.class);
		plugins.apply(LombokPlugin.class);
	}
}
