package com.rizaldi.phrinta.service.spring;

import com.rizaldi.phrinta.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rizaldi.phrinta.model.User internalUser = userService.get(username);
        if (internalUser == null) throw new UsernameNotFoundException(username + " doesn't exist in mongodb");
        return User.withUsername(internalUser.getUsername())
                .password(internalUser.getPassword())
                .roles(internalUser.getRoles().stream()
                        .map(Enum::name)
                        .toArray(String[]::new))
                .build();
    }
}
