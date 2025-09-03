package com.example.taskmanagerapi.controller;


import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody User user) {
        User savedUser = userService.registration(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        boolean success = userService.login(loginDto);
        if (success) {
            return ResponseEntity.ok("Login successful ✅");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password ❌");
        }
    }


}
