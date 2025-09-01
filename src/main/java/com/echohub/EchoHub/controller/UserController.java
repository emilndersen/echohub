package com.echohub.EchoHub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Внедрение зависимости через конструктор
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // This controller handles HTTP requests related to User entities.
    @GetMapping("/{id}") 
    public ResponseEntity<User> getUserById(@PathVariable Long id) {  // <- исправлено с @RequestParam на @PathVariable
        User user = userService.getUserById(id);
        // If the user is found, return it with HTTP status 200 OK.
        // If not found, return HTTP status 404 Not Found.
        // This method retrieves a user by their ID.
        // It uses the UserService to fetch the user from the database.
        if (user != null) {
            return ResponseEntity.ok(user); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
        return ResponseEntity.ok(userService.getUsers(username));
    }


    
    @GetMapping("/search")
    public ResponseEntity<Optional<User>> searchUsers(@RequestParam String query) {
        // This method searches for users based on a query string.
        // It uses the UserService to perform the search and returns the results.
        Optional<User> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // This method deletes a user by their ID.
        // It uses the UserService to remove the user from the database.
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}