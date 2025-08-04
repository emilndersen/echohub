package com.echohub.EchoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.echohub.EchoHub.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for password encoding
        // This is a strong hashing function that is suitable for storing passwords securely
        return new BCryptPasswordEncoder();
    }

    org.springframework.security.core.userdetails.UserDetails user = 
        org.springframework.security.core.userdetails.User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER") // автоматически добавит префикс ROLE_
            .build();


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests(requests -> requests
                        .antMatchers("/comments/**").hasRole("USER")
                        .antMatchers("/posts/**").hasRole("USER")
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/superadmin/**").hasRole("SUPERADMIN")
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                        .logout()
                        .permitAll());
    }
} 