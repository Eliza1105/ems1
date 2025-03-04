package ems.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MVCConfig implements WebMvcConfigurer { // MVC configuration for setting up view controllers
    public void addViewControllers(ViewControllerRegistry registry) { // Method for adding view controllers
        registry.addViewController("/login").setViewName("login"); // Specifies that a request to /login should display the template "login"
    }
}
