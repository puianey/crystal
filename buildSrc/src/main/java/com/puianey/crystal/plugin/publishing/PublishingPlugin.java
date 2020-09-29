package com.puianey.crystal.plugin.publishing;

import com.puianey.crystal.plugin.repositories.RepositoriesApply;
import java.util.Objects;
import org.gradle.api.NamedDomainObjectSet;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlatformPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.provider.Property;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.VariantVersionMappingStrategy;
import org.gradle.api.publish.maven.MavenPom;
import org.gradle.api.publish.maven.MavenPomDeveloperSpec;
import org.gradle.api.publish.maven.MavenPomScm;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.plugin.devel.plugins.JavaGradlePluginPlugin;

public class PublishingPlugin implements Plugin<Project> {

	public void apply(Project project) {
		PluginContainer plugins = project.getPlugins();
		plugins.apply(MavenPublishPlugin.class);
		PublishingExtension publishing = (PublishingExtension)project.getExtensions().getByType(PublishingExtension.class);
		(new RepositoriesApply()).apply(project, true);
		if (this.isUserPlugin(project)) {
			publishing.getPublications().withType(MavenPublication.class).all((mavenPublicationx) -> {
				this.customizeMavenPublication(mavenPublicationx, project);
			});
		} else {
			MavenPublication mavenPublication = (MavenPublication)publishing.getPublications().create("maven", MavenPublication.class);
			this.customizeMavenPublication(mavenPublication, project);
		}

	}

	private void customizeMavenPublication(MavenPublication publication, Project project) {
		this.customizePom(publication.getPom(), project);
		project.getPlugins().withType(JavaPlugin.class).all((javaPlugin) -> {
			JavaPluginExtension extension = (JavaPluginExtension)project.getExtensions().getByType(JavaPluginExtension.class);
			extension.withSourcesJar();
			if (!this.isUserPlugin(project) && ((Jar)project.getTasks().getByName("jar")).isEnabled()) {
				NamedDomainObjectSet var10000 = project.getComponents().matching((component) -> {
					return component.getName().equals("java");
				});
				Objects.requireNonNull(publication);
				var10000.all(publication::from);
				this.customizeJavaMavenPublication(publication, project);
			}

		});
		if (!this.isUserPlugin(project)) {
			project.getPlugins().withType(JavaPlatformPlugin.class).all((javaPlatformPlugin) -> {
				NamedDomainObjectSet var10000 = project.getComponents().matching((component) -> {
					return component.getName().equals("javaPlatform");
				});
				Objects.requireNonNull(publication);
				var10000.all(publication::from);
			});
		}

	}

	private void customizePom(MavenPom pom, Project project) {
		pom.getUrl().set("https://github.com/puianey/crystal");
		Property var10000 = pom.getName();
		Objects.requireNonNull(project);
		var10000.set(project.provider(project::getName));
		var10000 = pom.getDescription();
		Objects.requireNonNull(project);
		var10000.set(project.provider(project::getDescription));
		pom.developers(this::customizeDevelopers);
		pom.scm((scm) -> {
			this.customizeScm(scm, project);
		});
	}

	private void customizeJavaMavenPublication(MavenPublication publication, Project project) {
		publication.versionMapping((strategy) -> {
			strategy.usage("java-api", (mappingStrategy) -> {
				mappingStrategy.fromResolutionOf("runtimeClasspath");
			});
		});
		publication.versionMapping((strategy) -> {
			strategy.usage("java-runtime", VariantVersionMappingStrategy::fromResolutionResult);
		});
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
