package com.puianey.crystal.plugin;

import com.puianey.crystal.plugin.common.CommonPlugin;
import com.puianey.crystal.plugin.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin;

public class PluginPlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(JavaGradlePluginPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
	}
}
