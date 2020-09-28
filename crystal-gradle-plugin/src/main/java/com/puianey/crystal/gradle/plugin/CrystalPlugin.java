package com.puianey.crystal.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CrystalPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {

		project.getConfigurations().all(configuration ->
			configuration.getResolutionStrategy().eachDependency(rule -> {
				if (rule.getRequested().getModule().getName().equals("spring-boot-starter-logging")) {
					rule.useTarget("org.springframework.boot:spring-boot-starter-log4j2:$dependency.requested.version");
					rule.because("Use Log4j2 instead of Logback");
				}
			})
		);

//		project.getPlugins().apply(SpringBootPlugin.class);

	}
}
