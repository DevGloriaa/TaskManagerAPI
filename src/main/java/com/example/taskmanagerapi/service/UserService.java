package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.model.User;

public interface UserService {
    public User registration(User user);
    public User login(LoginDto user);
}
