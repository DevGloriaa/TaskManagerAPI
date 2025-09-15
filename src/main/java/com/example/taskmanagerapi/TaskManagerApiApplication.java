package com.example.taskmanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaskManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApiApplication.class, args);
        System.out.println("TaskManagerApiApplication started ✅");
    }
}
