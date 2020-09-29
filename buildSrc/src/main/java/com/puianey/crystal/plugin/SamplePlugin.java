package com.puianey.crystal.plugin;

import com.puianey.crystal.plugin.common.CommonPlugin;
import com.puianey.crystal.plugin.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ApplicationPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;

public class SamplePlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(JavaPlugin.class);
		plugins.apply(ApplicationPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
	}
}
