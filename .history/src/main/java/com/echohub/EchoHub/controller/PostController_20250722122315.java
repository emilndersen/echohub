package com.echohub.EchoHub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echohub.EchoHub.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
    // Здесь будет код контроллера для работы с постами
    // Например, методы для создания, получения, обновления и удаления постов
    // Также можно добавить методы для работы с тегами, если это необходимо
    private final PostService postService;

    // Внедрение зависимости через конструктор
    public PostController(PostService postService) {
        this.postService = postService;
    }

    
}