package com.example.taskmanagerapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "Server is alive at " + System.currentTimeMillis();
    }
}
