package com.quizapp.userservice.service;

import com.quizapp.userservice.web.dto.UserCreateRequest;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.dto.UserUpdateRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserDTO getById(Long userId);
    List<UserDTO> getAll();
    UserDTO create(@Valid UserCreateRequest request);
    UserDTO update(@Valid UserUpdateRequest request);
    String remove(Long userId);
}
