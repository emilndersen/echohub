package com.echohub.EchoHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserId(Long user); // Получить все посты по ID пользователя
    List<Comment> findByPostId(Long post); // Получить все комментарии по ID поста
    
}
