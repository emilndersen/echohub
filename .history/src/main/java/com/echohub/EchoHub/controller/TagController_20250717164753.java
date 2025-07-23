package com.echohub.EchoHub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.service.TagService;

@RestController
@RequestMapping("/tags")
// This controller handles HTTP requests related to Tag entities.
// It will include methods for creating, retrieving, updating, and deleting tags.
public class TagController {
    
    private final TagService tagService;

    // Dependency injection through constructor
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // Additional methods for handling tag operations will be added here.
    
}
