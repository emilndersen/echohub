package com.echohub.EchoHub.service;
import org.springframework.stereotype.Service;

import com.echohub.EchoHub.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
