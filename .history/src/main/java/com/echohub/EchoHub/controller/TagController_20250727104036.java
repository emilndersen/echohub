package com.echohub.EchoHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.Tag;
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

    // Here, methods for handling tag-related operations will be added in the future.
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags(@RequestParam(required = false) String name) {
        // This method retrieves all tags or filters by name if provided.
        return ResponseEntity.ok(tagService.getAllTags(name));
    }
        
    @GetMapping("/{name}")
    public ResponseEntity<Tag> getTagByName(@PathVariable Long name) {
        // This method retrieves a tag by its ID.
        return tagService.getTagByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
