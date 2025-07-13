package com.echohub.EchoHub.service;
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
        return postRepository.save(post);
    }

    // This method retrieves a post by its ID.
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
