package com.echohub.EchoHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.Post;
import com.echohub.EchoHub.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(User user); // Получить все посты по ID пользователя
}
