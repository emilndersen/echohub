package com.echohub.EchoHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
// This configuration class enables Spring MVC and sets up view resolution.
// It scans for components in the specified packages to find controllers and services.
@ComponentScan(basePackages = {
"com.echohub.EchoHub.config",
"com.echohub.EchoHub.controller",})
public class WebConfig {
    // This method configures the view resolver to use JSP files located in the /WEB-INF/views/ directory.
    // It sets the view class to JstlView, which allows for JSTL tags in JSPs.
    @Bean
    // This bean defines the view resolver for JSP views.
    public InternalResourceViewResolver viewResolver() {
        // Create an InternalResourceViewResolver instance to resolve views.
        // It sets the prefix and suffix for JSP files.
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // Set the view class to JstlView to support JSTL in JSPs.
        // Set the prefix to the directory where JSP files are located.
        resolver.setViewClass(JstlView.class);
        // Set the prefix and suffix for JSP files.
        resolver.setPrefix("/WEB-INF/views/");
        // Set the suffix to .jsp, indicating that the views are JSP files.
        resolver.setSuffix(".jsp");
        // Return the configured view resolver.
        return resolver;
    }    
}
