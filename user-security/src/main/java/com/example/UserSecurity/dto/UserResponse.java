package com.example.UserSecurity.dto;


import lombok.Getter;

import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component

public class UserResponse {

   private Long userId;
    private String username;
    private String message; // "User created successfully"
}
