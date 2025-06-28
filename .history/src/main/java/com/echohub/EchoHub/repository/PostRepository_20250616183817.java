package com.echohub.EchoHub.repository;

import java.util.List;
import com.echohub.EchoHub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {
    Lisy<Post> findByUserId(Long id);
}
