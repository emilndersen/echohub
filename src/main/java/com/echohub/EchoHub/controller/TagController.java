package com.echohub.EchoHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<Tag> getTagByName(@PathVariable String name) {
        // This method retrieves a tag by its name.
        return ResponseEntity.ok(tagService.getTagByName(name));
    }

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        // This method creates a new tag with the provided name.
        return ResponseEntity.ok(tagService.createTag(tag.getName()));
    }

    @PutMapping("/{name}")
    public ResponseEntity<Tag> updateTag(@PathVariable String name, @RequestBody Tag tag){
        return ResponseEntity.ok(tagService.updateTag(name, tag));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        // This method deletes a tag by its name.
        try {
            tagService.deleteTag(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}