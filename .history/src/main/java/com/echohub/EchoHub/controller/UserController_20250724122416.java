package com.echohub.EchoHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.service.UserService;



@RestController
@RequestMapping("users")
public class UserController {

    // This controller handles HTTP requests related to User entities.
    private final UserService userService;

    // Внедрение зависимости через конструктор
    public UserController(UserService userService) {
        this.userService = userService;
    }
   
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}