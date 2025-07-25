package com.echohub.EchoHub.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
