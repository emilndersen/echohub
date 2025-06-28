package com.echohub.EchoHub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.echohub.EchoHub.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
