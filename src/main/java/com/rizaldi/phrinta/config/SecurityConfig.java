package com.rizaldi.phrinta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .anyExchange().authenticated()
                .and().formLogin()
                .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService mapReactiveUserDetailsService() {
        UserDetails details = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("bamburuncing")
                .roles("ADMIN", "USER")
                .build();
        return new MapReactiveUserDetailsService(details);
    }
}
