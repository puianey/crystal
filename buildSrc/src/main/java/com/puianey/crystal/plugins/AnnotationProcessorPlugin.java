package com.puianey.crystal.plugins;

import com.puianey.crystal.plugins.common.CommonPlugin;
import com.puianey.crystal.plugins.publishing.PublishingPlugin;
import com.puianey.crystal.plugins.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.plugins.JavaPlugin;

import java.util.Arrays;

import static com.puianey.crystal.plugins.constant.Constants.AUTO_SERVICE;
import static com.puianey.crystal.plugins.constant.Constants.CONFIG_CONTAINER;

public class AnnotationProcessorPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {

		var plugins = project.getPlugins();

		plugins.apply(JavaLibraryPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
		plugins.apply(PublishingPlugin.class);

		plugins.withType(JavaPlugin.class, javaPlugin ->
			Arrays.stream(CONFIG_CONTAINER).forEach(configContainer ->
				project.getConfigurations().getByName(configContainer, config ->
					config.getDependencies().add(project.getDependencies().create(AUTO_SERVICE))
				)
			)
		);

	}
}

