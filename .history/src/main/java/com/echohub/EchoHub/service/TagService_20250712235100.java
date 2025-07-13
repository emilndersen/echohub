 package com.echohub.EchoHub.service;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Tag;
import com.echohub.EchoHub.repository.TagRepository;
 @Service
public class TagService {

    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag createTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tagRepository.save(tag);
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name).orElse(null);
    }
}
