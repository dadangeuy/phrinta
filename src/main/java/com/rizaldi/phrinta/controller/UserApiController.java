package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService service;

    public UserApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Mono<User> register(@RequestBody User user) {
        return service.insert(user);
    }
}
