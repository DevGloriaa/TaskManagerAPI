package com.example.taskmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TaskManagerApiApplication {

    @Value("${spring.data.mongodb.uri:NOT_FOUND}")
    private String mongoUri;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApiApplication.class, args);
        System.out.println("TaskManagerApiApplication started ✅");
    }

    @PostConstruct
    public void checkMongoUri() {
        System.out.println("👉 Mongo URI from Spring: " + mongoUri);
    }
}
