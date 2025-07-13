package com.echohub.EchoHub.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.echohub.EchoHub.model.UserRepository;


@Servicepublic class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    public void addUser(String username) {
        userRepository.saveUsername(username);
    }
}