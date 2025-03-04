package ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main class of Spring Boot application
@SpringBootApplication
// Annotation that enables autoconfiguration, component scanning, and Spring configuration
public class Application {

// The main method that starts the application

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }// Start the application using SpringApplication

}