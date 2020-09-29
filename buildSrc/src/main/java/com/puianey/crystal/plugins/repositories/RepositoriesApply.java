package com.puianey.crystal.plugins.repositories;

import org.gradle.api.Project;
import org.gradle.api.publish.PublishingExtension;

import java.util.Objects;

import static com.puianey.crystal.plugins.constant.Constants.*;

public class RepositoriesApply {

	public void apply(Project project, boolean publishing) {
		var repositoryHandler = publishing ? project.getExtensions().getByType(PublishingExtension.class).getRepositories() : project.getRepositories();
		repositoryHandler.maven(mavenRepository -> {

			mavenRepository.setUrl(
				publishing ?
					Objects.requireNonNull(String.valueOf(project.getVersion()).endsWith("SNAPSHOT") ? project.property(NEXUS_URL_SNAPSHOT) : project.property(NEXUS_URL_RELEASE)) :
					Objects.requireNonNull(project.property(NEXUS_URL_PUBLIC))
			);

			mavenRepository.credentials((credential) -> {
				credential.setUsername(String.valueOf(project.property(NEXUS_USERNAME)));
				credential.setPassword(String.valueOf(project.property(NEXUS_PASSWORD)));
			});
		});
	}
}
