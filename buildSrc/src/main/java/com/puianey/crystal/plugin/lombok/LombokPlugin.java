package com.puianey.crystal.plugin.lombok;

import com.puianey.crystal.constant.Constants;
import java.util.Arrays;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.plugins.JavaPlugin;

public class LombokPlugin implements Plugin<Project> {

	public void apply(Project project) {
		project.getPlugins().withType(JavaPlugin.class, (javaPlugin) -> {
			ConfigurationContainer configurationContainer = project.getConfigurations();
			Arrays.stream(Constants.CONFIG_CONTAINER).forEach((configContainer) -> {
				configurationContainer.getByName(configContainer, (config) -> {
					config.getDependencies().add(project.getDependencies().create("org.projectlombok:lombok"));
				});
			});
		});
	}
}
