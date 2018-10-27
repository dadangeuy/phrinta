package com.rizaldi.phrinta.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Builder
@Data
@Document(collection = "user")
public class User {
    @Id
    private String username;
    private String password;
    @Indexed(unique = true)
    private String name;
    private Set<Role> roles;

    public enum Role {
        USER, ADMIN;

        public GrantedAuthority toGrantedAuthority() {
            return new SimpleGrantedAuthority("ROLE_" + toString());
        }
    }

    public static User fromUsername(String username) {
        return builder()
                .username(username)
                .build();
    }
}
