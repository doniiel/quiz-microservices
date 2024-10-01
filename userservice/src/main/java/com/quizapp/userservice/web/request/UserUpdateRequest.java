package com.quizapp.userservice.web.request;

import com.quizapp.userservice.model.entity.UserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "Id is required")
    private Long id;

    private String username;
    private String password;
    private UserDetails userDetails;
}
