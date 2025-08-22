package com.echohub.EchoHub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;

@SpringBootApplication
public class EchoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoHubApplication.class, args);
		System.out.println("EchoHub Application has started successfully!");
		System.out.println("Visit http://localhost:8080 to access the application.");

		
	}

	@Bean
    CommandLineRunner run(UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@example.com");
            user.setPassword("pass");
            userRepository.save(user);

            System.out.println("User saved!");
        };
	}
}
