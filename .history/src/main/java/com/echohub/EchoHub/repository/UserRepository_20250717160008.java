package com.echohub.EchoHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.echohub.EchoHub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.username FROM User u")
    public List<String> findAllUsernames();

    // Custom query to find a user by username with roles eagerly fetched
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = ?1")
    Optional<User> findByUsernameWithRoles(String username);

    // Custom query to find a user by ID with roles eagerly fetched
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> findByIdWithRoles(Long id);

    // Custom query to find a user by email with roles eagerly fetched
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = ?1")
    Optional<User> findByEmailWithRoles(String email);
}
