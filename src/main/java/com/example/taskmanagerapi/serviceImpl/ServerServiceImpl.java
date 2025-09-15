package com.example.taskmanagerapi.serviceImpl;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServerServiceImpl {
    private final String serverUrl = "https://taskmanagerapi-1-142z.onrender.com/actuator/health";

    private final RestTemplate restTemplate;

    public ServerServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 600000)
    public void serverServiceImpl() {
        try {
            restTemplate.getForObject(serverUrl, String.class);
            System.out.println("Pinged server successfully at " + System.currentTimeMillis());
        } catch (Exception e) {
            System.err.println("Failed to ping server: " + e.getMessage());
        }
    }

}