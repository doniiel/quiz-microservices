package com.quizapp.userservice.web.controller;

import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.ResultDTO;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.jwt.request.RegisterRequest;
import com.quizapp.userservice.web.jwt.request.UserUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/quizapp/user/")
public class UserController {

    private final UserService userService;


    // Get user by ID
    // URL: http://localhost:8762/quizapp/user/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    // Get user by email
    // URL: http://localhost:8762/quizapp/user/byEmail/{email}
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // Get user by username
    // URL: http://localhost:8762/quizapp/user/byUsername/{username}
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        UserDTO user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    // Get all users (admin only)
    // URL: http://localhost:8762/quizapp/user/getAll
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    // Create a new user
    // URL: http://localhost:8762/quizapp/user/create
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    // Update an existing user
    // URL: http://localhost:8762/quizapp/user/update
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @userService.getUserById(#id).username == principal.username")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // Delete a user by ID
    // URL: http://localhost:8762/quizapp/user/{id}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @userService.getUserById(#id).username == principal.username")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }


    /* FEIGN CLIENT API */

    // Get all results for a user by ID
    // URL: http://localhost:8762/quizapp/user/results/{id}
    @GetMapping("/results/{id}")
    public ResponseEntity<List<ResultDTO>> getAllByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getResultsByUserId(userId));
    }
}
