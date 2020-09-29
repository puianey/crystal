package com.puianey.crystal.plugin;

import com.puianey.crystal.plugin.dependencies.DependenciesPlugin;
import com.puianey.crystal.plugin.publishing.PublishingPlugin;
import com.puianey.crystal.plugin.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.plugins.PluginContainer;

public class StarterPlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(JavaLibraryPlugin.class);
		plugins.apply(DependenciesPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
		plugins.apply(PublishingPlugin.class);
	}
}
