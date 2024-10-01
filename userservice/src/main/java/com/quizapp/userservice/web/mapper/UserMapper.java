package com.quizapp.userservice.web.mapper;

import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.web.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .userDetails(user.getUserDetails())
                .build();
    }

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .userDetails(userDTO.getUserDetails())
                .build();
    }
}
