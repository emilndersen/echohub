package com.echohub.EchoHub.service;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class UserService<UserRepository> {

    private final com.echohub.EchoHub.repository.UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = (com.echohub.EchoHub.repository.UserRepository) userRepository;
    }

    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    public void addUser(String username) {
        userRepository.saveUsername(username);
    }
}