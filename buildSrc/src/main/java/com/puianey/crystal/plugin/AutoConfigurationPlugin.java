package com.puianey.crystal.plugin;

import com.puianey.crystal.plugin.common.CommonPlugin;
import com.puianey.crystal.plugin.publishing.PublishingPlugin;
import com.puianey.crystal.plugin.repositories.RepositoriesPlugin;
import com.puianey.crystal.constant.Constants;

import java.util.Arrays;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;

public class AutoConfigurationPlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(JavaLibraryPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
		plugins.apply(PublishingPlugin.class);
		plugins.withType(JavaPlugin.class, (javaPlugin) -> {
			ConfigurationContainer configurationContainer = project.getConfigurations();
			Arrays.stream(Constants.AUTO_CONFIGURE).forEach((notation) -> {
				configurationContainer.getByName("annotationProcessor").getDependencies().add(project.getDependencies().create(notation));
			});
		});
	}
}

