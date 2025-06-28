package com.echohub.EchoHub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.Comment;
import com.echohub.EchoHub.model.Post;
import com.echohub.EchoHub.model.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
 List<Comment> findByUser(User user);
List<Comment> findByPost(Post post);
List<Comment> findByUserAndPost(User user, Post post);
List<Comment> findByContentContaining(String keyword);
List<Comment> findByUserAndContentContaining(User user, String keyword);
 List<Comment> findByPostAndContentContaining(Post post, String keyword);
 List<Comment> findByUserAndPostAndContentContaining(User user, Post post, String keyword);    
}
