package com.puianey.crystal.plugins.publishing;

import com.puianey.crystal.plugins.repositories.RepositoriesApply;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlatformPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.VariantVersionMappingStrategy;
import org.gradle.api.publish.maven.MavenPom;
import org.gradle.api.publish.maven.MavenPomDeveloperSpec;
import org.gradle.api.publish.maven.MavenPomScm;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin;

import java.util.Objects;

import static org.gradle.api.attributes.Usage.JAVA_API;
import static org.gradle.api.attributes.Usage.JAVA_RUNTIME;
import static org.gradle.api.plugins.JavaPlugin.JAR_TASK_NAME;
import static org.gradle.api.plugins.JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME;

public class PublishingPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		var plugins = project.getPlugins();
		plugins.apply(MavenPublishPlugin.class);
		var publishing = project.getExtensions().getByType(PublishingExtension.class);
		new RepositoriesApply().apply(project, true);
		if (isUserPlugin(project)) {
			publishing.getPublications().withType(MavenPublication.class).all(mavenPublication ->
				customizeMavenPublication(mavenPublication, project)
			);
			return;
		}
		publishing.getPublications().create(
			"maven",
			MavenPublication.class,
			mavenPublication -> customizeMavenPublication(mavenPublication, project)
		);
	}

	private void customizeMavenPublication(MavenPublication publication, Project project) {

		customizePom(publication.getPom(), project);
		project.getPlugins().withType(JavaPlugin.class).all(javaPlugin -> {
			project.getExtensions().getByType(JavaPluginExtension.class).withSourcesJar();
			if (!isUserPlugin(project) && ((Jar) project.getTasks().getByName(JAR_TASK_NAME)).isEnabled()) {
				project.getComponents().matching(component ->
					Objects.equals(component.getName(), "java")
				).all(publication::from);
				this.customizeJavaMavenPublication(publication);
			}
		});
		if (!this.isUserPlugin(project)) {
			project.getPlugins().withType(JavaPlatformPlugin.class).all(javaPlatformPlugin ->
				project.getComponents().matching(component ->
					Objects.equals(component.getName(), "javaPlatform")
				).all(publication::from)
			);
		}

	}

	private void customizePom(MavenPom pom, Project project) {

		pom.getUrl().set("https://spring.io/projects/spring-boot");
		pom.getName().set(project.provider(project::getName));
		pom.getDescription().set(project.provider(project::getDescription));
		pom.developers(this::customizeDevelopers);
		pom.scm((scm) -> customizeScm(scm, project));

	}

	private void customizeJavaMavenPublication(MavenPublication publication) {
		publication.versionMapping(strategy ->
			strategy.usage(JAVA_API, mappingStrategy ->
				mappingStrategy.fromResolutionOf(RUNTIME_CLASSPATH_CONFIGURATION_NAME)
			));
		publication.versionMapping(strategy ->
			strategy.usage(JAVA_RUNTIME, VariantVersionMappingStrategy::fromResolutionResult));
	}

	private void customizeDevelopers(MavenPomDeveloperSpec developers) {
		developers.developer((developer) -> {
			developer.getName().set("puianey");
			developer.getEmail().set("puianey@163.com");
			developer.getOrganization().set("Yoho8");
			developer.getOrganizationUrl().set("https://www.yoho8.com");
		});
	}

	private void customizeScm(MavenPomScm scm, Project project) {
		scm.getConnection().set("scm:github.com/puianey/crystal");
		scm.getDeveloperConnection().set("scm:github.com/puianey/crystal");
		scm.getUrl().set("https://github.com/puianey/crystal");
	}

	private boolean isUserPlugin(Project project) {
		return project.getPlugins().hasPlugin(JavaGradlePluginPlugin.class);
	}
}
