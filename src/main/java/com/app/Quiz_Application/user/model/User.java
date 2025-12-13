package com.app.Quiz_Application.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private String role;
}