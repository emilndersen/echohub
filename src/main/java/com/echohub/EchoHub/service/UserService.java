package com.echohub.EchoHub.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.echohub.EchoHub.model.Status;
import com.echohub.EchoHub.model.User;
import com.echohub.EchoHub.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService{

    private final UserRepository userRepository;

    // This constructor injects the UserRepository dependency
    // using Spring's dependency injection mechanism.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // This method retrieves all usernames from the repository.
    // It returns a list of usernames.
    public List<String> getAllUsernames() {
        return userRepository.findAllUsernames();
    }

    // This method adds a new user by saving the username to the repository.
    // It takes a username as a parameter and does not return anything.
    public void addUser(String username) {
        userRepository.findByUsername(username);

        User user = new User();
        user.setUsername(username);
        user.setPassword("defaultPassword"); // Set a default password or handle it as needed
        user.setEmail(username + "@example.com"); // Set a default email or handle it as needed
        user.setStatus(Status.ACTIVE); // Set a default status or handle it as needed
        userRepository.save(user); // Save the user entity to the repository
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

 public List<User> getUsers(String username) {
        if (username == null || username.isBlank()) {
            return userRepository.findAll(); // все пользователи
        } else {
            return userRepository.findByUsernameContainingIgnoreCase(username); 
            // ищет по подстроке (пример, зависит от репозитория)
        }
    }

    public Optional<User> searchUsers(String query) {
        // This method searches for users based on a query string.
        // It uses the UserRepository to perform the search and returns the results.
        return userRepository.findByUsername(query);
    }

    public User createUser(User user) {
        // Если нужно, можно добавить валидацию или проверку уникальности
        if (user.getPassword() == null) {
            user.setPassword("defaultPassword"); // Можно оставить дефолт, если пароль не указан
        }
        if (user.getEmail() == null) {
            user.setEmail(user.getUsername() + "@example.com");
        }
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        return userRepository.save(user);
    }

    public User addUser(User user) {
        // Можно задать значения по умолчанию, если какие-то поля null
        if (user.getPassword() == null) {
            user.setPassword("defaultPassword"); // или хэшировать
        }
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        if (user.getEmail() == null && user.getUsername() != null) {
            user.setEmail(user.getUsername() + "@example.com");
        }

        // Сохраняем пользователя в репозиторий
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setStatus(user.getStatus());
        return userRepository.save(existingUser);
    }


    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " does not exist");
        }
        userRepository.deleteById(id);
        return true;
    }
}