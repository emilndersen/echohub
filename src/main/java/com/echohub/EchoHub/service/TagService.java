package com.echohub.EchoHub.service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public List<Tag> getAllTags(String name){
        if(name == null || name.isEmpty()) {
            // If no name is provided, return all tags.
            return tagRepository.findAll();
        } else {
            // If a name is provided, filter tags by name.
            return tagRepository.findByNameContainingIgnoreCase(name);
        }
    }

    public Optional<Tag> updateTag(String name, Tag tag){
        return tagRepository.findByName(name)
            .map(existingTag -> {
                existingTag.setName(tag.getName());
                return tagRepository.save(existingTag);
            });
    }

    public boolean deleteTag(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new IllegalArgumentException("Tag with ID " + id + " does not exist");
        }
        tagRepository.deleteById(id);
        return true;
    }
}