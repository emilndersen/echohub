package com.echohub.EchoHub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.service.UserService;


@RestController
@RequestMapping("users")
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
        // This method retrieves all users, optionally filtered by username.
        // If a username is provided, it returns users matching that username.
        // Otherwise, it returns all users.
        Optional<List<User>> users = userService.getAllUsers(username);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
