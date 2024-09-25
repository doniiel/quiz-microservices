package com.quizapp.userservice.service.impl;

import com.quizapp.userservice.model.entity.Role;
import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.model.exception.InvalidUserInputException;
import com.quizapp.userservice.model.exception.UserAlreadyExistsException;
import com.quizapp.userservice.model.exception.UserNotFoundException;
import com.quizapp.userservice.repository.UserRepository;
import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.user.UserCreateRequest;
import com.quizapp.userservice.web.dto.user.UserDTO;
import com.quizapp.userservice.web.dto.user.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
    private User toEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
    }

    @Override
    public UserDTO getById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id: " + userId + " not found")
        );
       return mapToDTO(user);
    }

    @Override
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User with id: " + username + " not found")
        );
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO create (UserCreateRequest request) {
        String username = request.getUsername();
        boolean exist = userRepository.existsByUsername(username);
        if (exist) {
            throw new UserAlreadyExistsException("Username already exists: " + username);
        }
        if (!request.getPassword().equals(request.getConfirmationPassword())) {
            throw new InvalidUserInputException("Passwords do not match");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user  = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(hashedPassword)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    @Transactional
    public UserDTO update(UserUpdateRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new UserNotFoundException("User with username: " + request.getUsername() + " not found")
        );

        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            user.setPassword(hashedPassword);
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());

        userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    @Transactional
    public String remove(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User with id: " + userId + " not found")
        );
        userRepository.delete(user);
        return "Deleted user by id : "+ userId;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return toEntity(getByUsername(username));
    }

    @Override
    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
    }
}
