package com.echohub.EchoHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
    Lisy<Post> findByUserId(Long id);
}
