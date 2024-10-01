package com.quizapp.userservice.web.dto;

import com.quizapp.userservice.model.enums.Role;
import lombok.Data;

@Data
public class AuthUserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
}
