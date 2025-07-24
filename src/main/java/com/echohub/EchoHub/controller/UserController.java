package com.echohub.EchoHub.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



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
    public ResponseEntity<List<User>> getAllUsers(@PathVariable(required = false) String username) {
        // This method retrieves all users or filters by username if provided.
        // It uses the UserService to fetch the list of users from the database.
        Optional<List<User>> users = Optional.empty();
        return ResponseEntity.ok(users.orElseGet(List::of));
    }
    
    @GetMapping("/search")
    public ResponseEntity<Optional<User>> searchUsers(@PathVariable String query) {
        // This method searches for users based on a query string.
        // It uses the UserService to perform the search and returns the results.
        Optional<User> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Optional<User>> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user.getUsername()));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody String username) {
        // This method adds a new user by saving the username to the repository.
        // It takes a username as a parameter and does not return anything.
        userService.addUser(username);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id, user)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
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