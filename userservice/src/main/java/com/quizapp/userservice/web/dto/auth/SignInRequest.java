package com.quizapp.userservice.web.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {

    @Size(min = 5, max = 15, message = "Username must be between 5 and 50 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
