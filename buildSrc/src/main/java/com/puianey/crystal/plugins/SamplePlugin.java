package com.puianey.crystal.plugins;

import com.puianey.crystal.plugins.common.CommonPlugin;
import com.puianey.crystal.plugins.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ApplicationPlugin;
import org.gradle.api.plugins.JavaPlugin;

public class SamplePlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(JavaPlugin.class);
		plugins.apply(ApplicationPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
	}
}
