package com.echohub.EchoHub.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.echohub.EchoHub.model.Role;
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

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles(Set.of(Role.ROLE_USER))
            .build();

        return new InMemoryUserDetailsManager(user);
    }

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