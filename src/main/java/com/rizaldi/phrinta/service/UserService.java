package com.rizaldi.phrinta.service;

import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class UserService {
    private static final InternalError PASSWORD_NOT_MATCH = new InternalError("Password not match");
    private static final Set<User.Role> BASIC_ROLES = Collections.singleton(User.Role.USER);
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public UserService(PasswordEncoder encoder, UserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    public User get(String username) {
        return repository.findById(username).orElse(null);
    }

    public User insert(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(BASIC_ROLES);
        return repository.insert(user);
    }

    public User update(User user) {
        boolean authorized = repository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (authorized) return repository.save(user);
        throw PASSWORD_NOT_MATCH;
    }
}
