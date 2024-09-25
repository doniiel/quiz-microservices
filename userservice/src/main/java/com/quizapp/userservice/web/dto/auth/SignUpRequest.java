package com.quizapp.userservice.web.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    @Size(min = 5, max = 15, message = "Username must be between 5 and 50 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 5, max = 255, message = "Email address must be between 5 and 255 characters")
    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "Email address must be in the format user@example.com")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
