package com.echohub.EchoHub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.echohub.EchoHub.model.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{

    @Bean
    public PasswordEncoder passwordEncoder() {
        
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure global authentication settings here
        // For example, you can set up in-memory authentication or JDBC authentication
        // auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
      	auth.CreateUser(User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build());
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}adminpass").roles("ADMIN")
                .and()
                .withUser("superadmin").password("{noop}superadminpass").roles("SUPERADMIN");
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