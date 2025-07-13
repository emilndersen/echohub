package src.main.java.com.echohub.EchoHub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Tag;
import com.echohub.EchoHub.repository.TagRepository;


@Service
public class CommentService {
    private final TagRepository tagRepository;

    public CommentService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Optional<Tag> getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
