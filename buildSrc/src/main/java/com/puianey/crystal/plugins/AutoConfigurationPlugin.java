package com.puianey.crystal.plugins;

import com.puianey.crystal.plugins.common.CommonPlugin;
import com.puianey.crystal.plugins.publishing.PublishingPlugin;
import com.puianey.crystal.plugins.repositories.RepositoriesPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.plugins.JavaPlugin;

import java.util.Arrays;

import static com.puianey.crystal.plugins.constant.Constants.AUTO_CONFIGURE;
import static org.gradle.api.plugins.JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME;

public class AutoConfigurationPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(JavaLibraryPlugin.class);
		plugins.apply(CommonPlugin.class);
		plugins.apply(RepositoriesPlugin.class);
		plugins.apply(PublishingPlugin.class);
		plugins.withType(JavaPlugin.class, javaPlugin ->
			Arrays.stream(AUTO_CONFIGURE).forEach(notation ->
				project.getConfigurations().getByName(ANNOTATION_PROCESSOR_CONFIGURATION_NAME).getDependencies()
					.add(project.getDependencies().create(notation))
			)
		);
	}
}

