package com.echohub.EchoHub.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Post;
import com.echohub.EchoHub.repository.PostRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PostService {
    private final PostRepository postRepository;

    // This service class handles operations related to posts.
    // It uses the PostRepository to interact with the database.
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // This method creates a new post with the given content.
    public Post createPost(String content) {
        Post post = new Post();
        post.setContent(content);
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        return postRepository.save(post);
    }

    // This method retrieves a post by its ID.
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + id + " not found"));
    }

    // This method retrieves all posts.
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // This method deletes a post by its ID.
    public boolean deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post with ID " + id + " does not exist");
        }
        postRepository.deleteById(id);
        return false;
    }

    public Post updatePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with ID " + id + " not found"));

        // Обновляем поля
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setTags(post.getTags());
        // и другие поля по необходимости

        return postRepository.save(existingPost);
    }
}
