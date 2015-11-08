package org.javaday.training.spring.rest.lab04;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author yaroslav.yermilov
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Lab04Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab04Application.class, args);
    }
}