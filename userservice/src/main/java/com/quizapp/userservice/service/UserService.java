package com.quizapp.userservice.service;

import com.quizapp.userservice.web.dto.ResultDTO;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.jwt.request.RegisterRequest;
import com.quizapp.userservice.web.jwt.request.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAll();
    UserDTO updateUser(UserUpdateRequest request);
    UserDTO createUser(RegisterRequest request);
    void deleteUserById(Long id);


    /* Feign Client method*/
    List<ResultDTO> getResultsByUserId(Long userId);
}
