package com.echohub.EchoHub.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Status;
import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;

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
        userRepository.findByUsername(username);

        User user = new User();
        user.setUsername(username);
        user.setPassword("defaultPassword"); // Set a default password or handle it as needed
        user.setEmail(username + "@example.com"); // Set a default email or handle it as needed
        user.setStatus(Status.ACTIVE); // Set a default status or handle it as needed
        userRepository.save(user); // Save the user entity to the repository
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getAllUsers(String username) {
        if (username != null && !username.isEmpty()) {
            return userRepository.findByUsername(username);
        }
        return Optional.empty();
    }

    public Optional<User> searchUsers(String query) {
        // This method searches for users based on a query string.
        // It uses the UserRepository to perform the search and returns the results.
        return userRepository.findByUsername(query);
    }

    public Optional<User> createUser(String username){
        // This method creates a new user with the provided username.
        // It uses the UserRepository to save the user and returns the created user.
        User user = new User();
        user.setUsername(username);
        user.setPassword("defaultPassword"); // Set a default password or handle it as needed
        user.setEmail(username + "@example.com"); // Set a default email or handle it as needed
        user.setStatus(Status.ACTIVE); // Set a default status or handle it as needed
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> updateUser(Long id, User user){
        if(!userRepository.existsById(id)) {
            return Optional.empty();
        }
        user.setId(id);
        return Optional.of(userRepository.save(user));
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
        return true;
    }
}