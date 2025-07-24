package com.echohub.EchoHub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Comment;
import com.echohub.EchoHub.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    // This service class handles operations related to comments.
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // This method creates a new comment with the given content.
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    // This method saves a new comment.
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // This method updates an existing comment.
    public Comment updateComment(Long id, String content) {
        // First, we check if the comment exists by its ID.
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(content);
            return commentRepository.save(comment);
        } else {
            // If the comment does not exist, we throw an exception.
            throw new IllegalArgumentException("Comment not found with id: " + id);
        }
    }

    // This method deletes a comment by its ID.
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    public List <Comment> getAllComments() {
        // This method retrieves all comments from the repository.
        return commentRepository.findAll();
    }
    
}
