package com.echohub.EchoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for password encoding
        // This is a strong hashing function that is suitable for storing passwords securely
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        // Create an in-memory user details service with a single user
        // This is useful for testing and development purposes
        var encoder = passwordEncoder();

        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername("user")
                .password(encoder.encode("password")) // Encode the password
                .roles("USER") // Assign the role "USER"
                .build();

        var userAdmin = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN") // Assign the role "ADMIN"
                .build();

        var userModerator = org.springframework.security.core.userdetails.User
                .withUsername("moderator")
                .password(encoder.encode("moderator"))
                .roles("MODERATOR") // Assign the role "MODERATOR"
                .build();

        var manager = new InMemoryUserDetailsManager();
        manager.createUser(userDetails);
        manager.createUser(userAdmin);
        manager.createUser(userModerator);
        return manager;
    }
}