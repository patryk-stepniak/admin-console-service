package com.adminconsole.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class UserService {

    private final UserRepository userRepository;

    private final String defaultPassword;

    UserService(UserRepository userRepository, @Value("${security.account.defaultPassword}") String defaultPassword) {
        this.userRepository = userRepository;
        this.defaultPassword = defaultPassword;
    }

    Map<String, Boolean> getUsers() {
        return userRepository.findAll().stream().collect(Collectors.toMap(User::getUsername, User::isActive));
    }

    void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    void addUser(String username) {
        if (userRepository.findById(username).isPresent()) {
            throw new EntityExistsException("User already exists.");
        }
        userRepository.save(new User(username, defaultPassword));
    }

    void setActive(String username, boolean isActive) {
        final Optional<User> user = userRepository.findById(username).map(u -> updateActiveOnUser(isActive, u));
        if (user.isEmpty()) {
            throw new EntityExistsException("User doesnt not exist.");
        }
        userRepository.save(user.get());
    }

    private User updateActiveOnUser(boolean isActive, User user) {
        user.setActive(isActive);
        return user;
    }

}
