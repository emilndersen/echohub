package com.echohub.EchoHub.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Post;
import com.echohub.EchoHub.repository.PostRepository;

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
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
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

    public Optional<Post> updatePost(Long id, Post post) {
        if (!postRepository.existsById(id)) {
            return Optional.empty();
        }
        post.setId(id);
        return Optional.of(postRepository.save(post));
    }
}
