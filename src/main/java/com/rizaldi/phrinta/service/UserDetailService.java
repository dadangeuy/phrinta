package com.rizaldi.phrinta.service;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailService implements ReactiveUserDetailsService {
    private final PasswordEncoder encoder;
    private final UserService userService;

    public UserDetailService(PasswordEncoder encoder, UserService userService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        if (username.equals("admin")) {
            return Mono.just(User.withUsername("admin")
                    .password(encoder.encode("bamburuncing"))
                    .roles("USER", "ADMIN")
                    .build());
        }

        return userService.get(username)
                .map(user -> User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .build());
    }
}
