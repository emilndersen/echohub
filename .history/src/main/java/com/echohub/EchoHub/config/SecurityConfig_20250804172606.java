package com.echohub.EchoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{

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
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(user);
        manager.createUser(userAdmin);
        manager.createUser(userModerator);
        return manager;
    }
}