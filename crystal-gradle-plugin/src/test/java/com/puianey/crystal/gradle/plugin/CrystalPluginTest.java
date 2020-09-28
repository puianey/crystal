package com.puianey.crystal.gradle.plugin;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

public class CrystalPluginTest {


	@Test
	public void test() {
		Project project = ProjectBuilder.builder().build();
		project.getPlugins().apply(CrystalPlugin.class);
	}

}
