package com.quizapp.userservice.web.dto.user;


import com.quizapp.userservice.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
