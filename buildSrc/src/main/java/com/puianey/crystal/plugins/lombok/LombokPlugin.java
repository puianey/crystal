package com.puianey.crystal.plugins.lombok;

import com.puianey.crystal.plugins.constant.Constants;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

import java.util.Arrays;

public class LombokPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {

		project.getPlugins().withType(JavaPlugin.class, javaPlugin ->
			Arrays.stream(Constants.CONFIG_CONTAINER).forEach(configContainer ->
				project.getConfigurations().getByName(configContainer, config ->
					config.getDependencies().add(project.getDependencies().create(Constants.LOMBOK))
				)
			)
		);
	}

}
