package src.main.java.com.echohub.EchoHub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Tag;
import com.echohub.EchoHub.repository.TagRepository;


@Service
public class CommentService {
    // This service class handles operations related to comments.
    // It uses the TagRepository to interact with the database for tag-related operations.
    private final TagRepository tagRepository;

    // Constructor to inject the TagRepository dependency.
    public CommentService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // This method creates a new tag with the given name.
    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
