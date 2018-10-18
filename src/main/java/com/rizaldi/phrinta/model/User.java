package com.rizaldi.phrinta.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Builder
@Data
@Document
public class User {
    @Id
    private String username;
    private String password;
    @Indexed(unique = true)
    private String name;
    private Set<Role> roles;

    public enum Role {
        USER, ADMIN
    }
}
