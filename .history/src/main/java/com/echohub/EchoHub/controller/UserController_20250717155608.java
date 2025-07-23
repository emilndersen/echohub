package com.echohub.EchoHub.controller;

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

    private final UserService userService;

    // Внедрение зависимости через конструктор
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // This controller handles HTTP requests related to User entities.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {  // <- исправлено с @RequestParam на @PathVariable
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
