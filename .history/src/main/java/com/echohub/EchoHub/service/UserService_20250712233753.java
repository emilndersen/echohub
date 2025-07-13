package com.echohub.EchoHub.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;
import com.echohub.EchoHub.model.Status;


@Service
public class UserService{



    private final UserRepository userRepository;

    // This constructor injects the UserRepository dependency
    // using Spring's dependency injection mechanism.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // This method retrieves all usernames from the repository.
    // It returns a list of usernames.
    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    // This method adds a new user by saving the username to the repository.
    // It takes a username as a parameter and does not return anything.
    public void addUser(String username) {
        userRepository.saveUsername(username);

        User user = new User();
        user.setUsername(username);
        user.setPassword("defaultPassword"); // Set a default password or handle it as needed
        user.setEmail(username + "@example.com"); // Set a default email or handle it as needed
        user.setStatus("active"); // Set a default status or handle it as needed   
        userRepository.save(user); // Save the user entity to the repository

    }
}