package com.echohub.EchoHub.service;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final com.echohub.EchoHub.repository.UserRepository userRepository;

    // This constructor injects the UserRepository dependency
    // using Spring's dependency injection mechanism.
    public UserService(com.echohub.EchoHub.repository.UserRepository userRepository) {
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
    }
}