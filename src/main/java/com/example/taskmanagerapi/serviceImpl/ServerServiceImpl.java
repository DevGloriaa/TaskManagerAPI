package com.example.taskmanagerapi.serviceImpl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServerServiceImpl {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String serverUrl = "https://taskmanagerapi-1-142z.onrender.com/users/ping";

    @Scheduled(fixedRate = 300000)
    public void pingServer() {
        try {
            String response = restTemplate.getForObject(serverUrl, String.class);
            System.out.println("Ping successful: " + response);
        } catch (Exception e) {
            System.err.println("Failed to ping server: " + e.getMessage());
        }
    }
}
