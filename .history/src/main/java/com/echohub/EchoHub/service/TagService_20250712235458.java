 package com.echohub.EchoHub.service;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Tag;
import com.echohub.EchoHub.repository.TagRepository;
 @Service
public class TagService {
    
    // This service class handles operations related to tags.
    // It uses the TagRepository to interact with the database.
    // The TagRepository is injected through the constructor.
    private final TagRepository tagRepository;
    // Constructor to inject the TagRepository dependency.
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // This method creates a new tag with the given name.
    public Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    // This method retrieves a tag by its name.
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name).orElse(null);
    }
}
