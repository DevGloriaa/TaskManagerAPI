package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.exceptions.HandleUserDoesNotExistException;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User registration(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        return newUser;
    }

    @Override
    public User login(LoginDto dto) {
        User existing = userRepository.findByEmailIgnoreCase(dto.getUsername())
                .orElseThrow(() -> new HandleUserDoesNotExistException("User does not exist"));
        return existing;
    }
}
