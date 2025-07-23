package com.echohub.EchoHub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("users")
public class UserController {

    // This controller handles HTTP requests related to User entities.
    @GetMapping("/{id}")
    public String getMethodName(@PathVariable String id) {  // <- исправлено с @RequestParam на @PathVariable
        return "User id: " + id;
    }
}
