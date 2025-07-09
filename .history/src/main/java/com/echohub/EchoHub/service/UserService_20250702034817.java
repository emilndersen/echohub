package com.echohub.EchoHub.service;

import org.springframework.stereotype.Service;
import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;

@Service
public class UserService {
    // Add methods for user-related operations here
    // For example, methods to create, update, delete, and retrieve users
    // This service can interact with the UserRepository to perform database operations

    public void createUser(String username, String password) {
        // Logic to create a user
        User user = new User(username, password);
        userRepository.save(user);
    }

    public void updateUser(String userId, String newUsername) {
        // Logic to update a user
        User user = userRepository.findById(userId);
        if (user != null) {
            user.setUsername(newUsername);
            userRepository.save(user);
        }
    }

    public void deleteUser(String userId) {
        // Logic to delete a user
        User user = userRepository.findById(userId);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public String getUser(String userId) {
        // Logic to retrieve a user
        User user = userRepository.findById(userId);
        return user != null ? user.toString() : "User not found";
    }
}
