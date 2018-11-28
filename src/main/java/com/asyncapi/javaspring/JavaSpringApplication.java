package com.asyncapi.javaspring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaSpringApplication {

	private static final Log LOGGER = LogFactory.getLog(JavaSpringApplication.class);

	/**
	 * Load the Spring Integration Application Context
	 *
	 * @param args - command line arguments
	 */
	public static void main(final String... args) {
		SpringApplication.run(JavaSpringApplication.class, args);

	}



}
