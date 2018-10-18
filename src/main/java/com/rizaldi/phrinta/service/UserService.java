package com.rizaldi.phrinta.service;

import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    Mono<User> get(String username) {
        return repository.findById(username);
    }

    Mono<User> insert(User user) {
        return repository.insert(user);
    }

    Mono<User> save(User user) {
        return repository.save(user);
    }
}
