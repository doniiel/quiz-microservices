package com.quizapp.userservice.web.controller;

import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.user.UserCreateRequest;
import com.quizapp.userservice.web.dto.user.UserDTO;
import com.quizapp.userservice.web.dto.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

    @GetMapping("/get-admin")
    public void getAdmin() {
        userService.getAdmin();
    }
}
