package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.model.User;

public interface UserService {
    User registration(User user);
    boolean login(LoginDto loginDto);
}
