package com.lykke.algos;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Loads environment variables.
 */
public class Environment {

	/**
	 * The prefix a placeholder value. All placeholder values are starting with this
	 * string
	 */
	private static final String PLACEHOLDER_PREFIX = "#{";
	/**
	 * Properties of the current environment.
	 */
	private static Properties properties = new Properties();

	/**
	 * Load the current environment properties
	 *
	 * @throws IOException
	 */
	public static void load() throws IOException {
		final String propertyFileName = "hft.properties";
		try (InputStream is = Algo.class.getClassLoader().getResourceAsStream(propertyFileName)) {
			if (is != null) {
				properties.load(is);
			}
		}

		verifyEnvironmentVariablesAreNotDefault();
	}

	/**
	 * Get environment variable
	 *
	 * @param variable
	 * @return
	 */
	public static String getVariable(String variable) {
		return properties.getProperty(variable);
	}

	/**
	 * Verifies that all of the environment variables are provided correctly and all
	 * placeholder are replaced with actual values
	 */
	private static void verifyEnvironmentVariablesAreNotDefault() {
		List<String> envVariablesWithDefaultValues = new ArrayList<>();

		for (String key : properties.stringPropertyNames()) {
			String value = getVariable(key);
			if (value == null || "".equals(value) || value.startsWith(PLACEHOLDER_PREFIX)) {
				envVariablesWithDefaultValues.add(key);
			}
		}

		if (!envVariablesWithDefaultValues.isEmpty()) {
			throw new RuntimeException(String.format("The following of the required properties were *NOT* set: %s",
					String.join(",", envVariablesWithDefaultValues)));
		}
	}
}