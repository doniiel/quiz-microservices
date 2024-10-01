package com.quizapp.authservice.dto;

import com.quizapp.authservice.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private String email;

}
