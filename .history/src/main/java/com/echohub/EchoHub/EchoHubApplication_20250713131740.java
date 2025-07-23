package com.echohub.EchoHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EchoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoHubApplication.class, args);
		System.out.println("EchoHub Application has started successfully!");
		System.out.println("Visit http://localhost:8080 to access the application.");
	}
}
