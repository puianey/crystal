package com.puianey.crystal.plugin.repositories;

import javax.annotation.Nonnull;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RepositoriesPlugin implements Plugin<Project> {

	public void apply(@Nonnull Project project) {
		(new RepositoriesApply()).apply(project, false);
	}
}
