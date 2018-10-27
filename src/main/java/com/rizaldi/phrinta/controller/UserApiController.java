package com.rizaldi.phrinta.controller;

import com.rizaldi.phrinta.model.User;
import com.rizaldi.phrinta.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService service;

    public UserApiController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.insert(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
