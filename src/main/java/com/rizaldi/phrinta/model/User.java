package com.rizaldi.phrinta.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Data
public class User {
    @Id
    private String username;
    private String password;
    @Indexed(unique = true)
    private String name;
}
