package com.quizapp.authservice.request;

import lombok.Data;
import lombok.Getter;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
