package com.quizapp.userservice.web.controller;

import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.UserCreateRequest;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.dto.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/quizapp/user/")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long userId) {
        return userService.getById(userId);
    }

    @GetMapping("")
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("")
    public UserDTO createUser(@RequestBody UserCreateRequest request) {
        return userService.create(request);
    }

    @PutMapping("")
    public UserDTO updateUser(@RequestBody UserUpdateRequest request) {
        return userService.update(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        return new ResponseEntity<>(userService.remove(userId), HttpStatus.OK);
    }
}
