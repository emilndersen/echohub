package com.echohub.EchoHub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // Dependency injection through constructor
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Additional methods for handling comment operations will be added here.
}
