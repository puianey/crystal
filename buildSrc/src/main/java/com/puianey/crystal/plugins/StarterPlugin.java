package com.puianey.crystal.plugins;

import com.puianey.crystal.plugins.dependencies.DependenciesPlugin;
import com.puianey.crystal.plugins.publishing.PublishingPlugin;
import com.puianey.crystal.plugins.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;

public class StarterPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(JavaLibraryPlugin.class);
		plugins.apply(DependenciesPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
		plugins.apply(PublishingPlugin.class);
	}
}
