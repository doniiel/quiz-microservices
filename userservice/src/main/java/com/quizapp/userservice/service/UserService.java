package com.quizapp.userservice.service;

import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.web.dto.user.UserCreateRequest;
import com.quizapp.userservice.web.dto.user.UserDTO;
import com.quizapp.userservice.web.dto.user.UserUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDTO getById(Long userId);
    UserDTO getByUsername(String username);
    List<UserDTO> getAll();
    UserDTO create(@Valid UserCreateRequest request);
    UserDTO update(@Valid UserUpdateRequest request);
    String remove(Long userId);
    UserDetailsService userDetailsService();
    User getCurrentUser();
    void getAdmin();
}
