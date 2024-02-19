package com.demo.jpabasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**	About CommandLineRunner and it's alternatives:
 * In Spring Boot, the CommandLineRunner interface provides a simple way to execute code when your application starts. It's ideal for tasks like:
 *
 * 	Seeding your database: You can insert initial data like dummy users, entries, or configurations before the application becomes fully operational.
 *
 * 	Performing initialization tasks: This could involve establishing connections to external services, initializing logs, or loading specific resources.
 *
 * 	Implementing custom startup functionalities: If your application has unique startup requirements, CommandLineRunner can handle them efficiently.
 *
 * Here's how it works:
 *
 * 	Implementation: You create a class that implements the CommandLineRunner interface.
 *
 * 	Override the run method: This method defines the code that will be executed when your application starts. It takes a String[] args parameter that allows you to access any arguments passed through the command line.
 *
 * 	Spring manages the execution: Spring automatically identifies any beans implementing CommandLineRunner and calls their run method after the application context is fully initialized.
 *
 * Benefits of using CommandLineRunner:
 *
 * 	Simple and flexible: It's an easy way to add custom startup logic without modifying your main application class.
 *
 * 	Decoupled logic: Your startup code is separated from the main application flow, promoting better organization and maintainability.
 *
 * 	Testable: You can easily unit test your CommandLineRunner implementation to ensure it behaves as expected.
 *
 * Alternatives to CommandLineRunner:
 *
 * 	ApplicationRunner: Similar to CommandLineRunner, but provides access to ApplicationArguments instead of a raw string array, offering richer information about command-line arguments.
 *
 * 	Initializing beans: You can mark beans with the @PostConstruct annotation to execute specific logic after bean creation within the Spring context.
 */
@SpringBootApplication
public class SpringbootJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaDemoApplication.class, args);
	}

}
