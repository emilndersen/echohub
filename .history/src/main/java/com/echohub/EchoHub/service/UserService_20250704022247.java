package com.echohub.EchoHub.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Business logic for comments
    // This could include methods for creating, updating, deleting, and retrieving comments
    
}
