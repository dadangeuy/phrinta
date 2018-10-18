package com.rizaldi.phrinta.service;

import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final PasswordEncoder encoder;
    private final UserRepository repository;
    private final InternalError passwordNotMatch = new InternalError("Password not match");

    public UserService(PasswordEncoder encoder, UserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    Mono<User> get(String username) {
        return repository.findById(username);
    }

    Mono<User> insert(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return repository.insert(user);
    }

    Mono<User> save(User user) {
        return get(user.getUsername())
                .map(User::getPassword)
                .map(password -> user.getPassword().equals(password))
                .flatMap(isMatch -> isMatch ? repository.save(user) : Mono.error(passwordNotMatch));
    }
}
