package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {
    public String performTask() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("🔧 Task performed.");
        return "Success";
    }
}