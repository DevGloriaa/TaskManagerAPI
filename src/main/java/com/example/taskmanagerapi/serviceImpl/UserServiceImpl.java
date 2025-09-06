package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.exceptions.HandleUserDoesNotExistException;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
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
        newUser.setIsverified(false);


        return userRepository.save(newUser);
    }


    @Override
    public boolean login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new HandleUserDoesNotExistException("User does not exist"));
        return loginDto.getPassword().equals(user.getPassword());
    }

}
