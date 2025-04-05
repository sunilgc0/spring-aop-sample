package com.example.aop;

import com.example.aop.service.ExampleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAopSampleApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ExampleService service) {
        return args -> service.performTask();
    }
}