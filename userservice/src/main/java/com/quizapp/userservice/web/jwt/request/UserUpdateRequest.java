package com.quizapp.userservice.web.jwt.request;

import com.quizapp.userservice.model.entity.UserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    private Long id;
    private String username;
    private String password;
    private UserDetails userDetails;
}
