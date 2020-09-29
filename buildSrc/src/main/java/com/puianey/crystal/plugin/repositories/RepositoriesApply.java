package com.puianey.crystal.plugin.repositories;

import java.util.Objects;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.publish.PublishingExtension;

public class RepositoriesApply {

	public void apply(Project project, boolean publishing) {
		RepositoryHandler repositoryHandler = publishing ? ((PublishingExtension)project.getExtensions().getByType(PublishingExtension.class)).getRepositories() : project.getRepositories();
		repositoryHandler.maven((mavenRepository) -> {
			if (publishing) {
				mavenRepository.setUrl(Objects.requireNonNull(String.valueOf(project.getVersion()).endsWith("SNAPSHOT") ? project.property("nexus_url_snapshot") : project.property("nexus_url_release")));
			} else {
				mavenRepository.setUrl(Objects.requireNonNull(project.property("nexus_url_public")));
			}

			mavenRepository.credentials((credential) -> {
				credential.setUsername(String.valueOf(project.property("nexus_username")));
				credential.setPassword(String.valueOf(project.property("nexus_password")));
			});
		});
	}
}
