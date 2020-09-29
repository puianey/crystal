package com.puianey.crystal.plugins.repositories;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RepositoriesPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		new RepositoriesApply().apply(project, false);
	}
}
