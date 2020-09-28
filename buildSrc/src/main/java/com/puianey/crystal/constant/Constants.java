package com.puianey.crystal.constant;

import org.gradle.api.plugins.JavaPlugin;

/**
 * @Author: puianey
 * @Date: 2018/9/14 20:52
 */
public interface Constants {

	/**
	 * AUTO_CONFIGURE
	 */
	String[] AUTO_CONFIGURE = {
		"org.springframework.boot:spring-boot-autoconfigure-processor",
		"org.springframework.boot:spring-boot-configuration-processor"
	};

	/**
	 * CONFIG_CONTAINER
	 */
	String[] CONFIG_CONTAINER = {
		JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME,
		JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME,
		JavaPlugin.TEST_COMPILE_ONLY_CONFIGURATION_NAME,
		JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME
	};

	/**
	 * LOMBOK
	 */
	String LOMBOK = "org.projectlombok:lombok";

	/**
	 * AUTO_SERVICE
	 */
	String AUTO_SERVICE = "com.google.auto.service:auto-service";

	String NEXUS_URL_PUBLIC = "nexus_url_public";

	String NEXUS_URL_RELEASE = "nexus_url_release";

	String NEXUS_URL_SNAPSHOT = "nexus_url_snapshot";

	String NEXUS_USERNAME = "nexus_username";

	String NEXUS_PASSWORD = "nexus_password";

}
