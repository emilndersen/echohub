package com.echohub.EchoHub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.model.Comment;
import com.echohub.EchoHub.service.CommentService;


@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    // Dependency injection through constructor
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // This controller handles HTTP requests related to Comment entities.
    

    // Additional methods for handling comment operations will be added here.

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.saveComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody String content) {
        Comment updated = commentService.updateComment(id, content);
        return updated != null
                ? ResponseEntity.ok(updated)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}