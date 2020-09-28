package com.puianey.crystal.auto.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

@UtilityClass
public class FactoriesFiles {

	/**
	 * 写出 spring.factories 文件
	 *
	 * @param factories factories 信息
	 * @param output    输出流
	 */
	@SneakyThrows
	public void writeFactoriesFile(Map<String, Set<String>> factories,
								   OutputStream output) {

		var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		factories.forEach((k, v) -> {
			Optional.ofNullable(v)
				.filter(set -> !set.isEmpty())
				.ifPresent(values -> {
					try {
						writer.write(k);
						writer.write("=\\\n  ");
						StringJoiner joiner = new StringJoiner(",\\\n  ");
						values.forEach(joiner::add);
						writer.write(joiner.toString());
						writer.newLine();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}

				});
		});
		writer.flush();
		output.close();

	}

	/**
	 * 写出 spring-devtools.properties
	 *
	 * @param projectName 项目名
	 * @param output      输出流
	 */
	@SneakyThrows
	public void writeDevToolsFile(String projectName,
								  OutputStream output) {
		var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		// restart.include.mica-cloud=/mica-cloud[\\w-]+\.jar
		var format = "restart.include.%s=/%s[\\\\w-]+\\.jar";
		writer.write(String.format(format, projectName, projectName));
		writer.flush();
		output.close();
	}

}
