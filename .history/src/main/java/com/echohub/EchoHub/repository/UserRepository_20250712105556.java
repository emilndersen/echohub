package com.echohub.EchoHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    public List<String> findAllUsernames();

    public void saveUsername(String username);
}
