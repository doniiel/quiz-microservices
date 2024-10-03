package com.quizapp.userservice.service.impl;

import com.quizapp.userservice.client.ResultClient;
import com.quizapp.userservice.model.enums.Role;
import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.model.exception.NotFoundException;
import com.quizapp.userservice.model.exception.UserAlreadyExistsException;
import com.quizapp.userservice.repository.UserRepository;
import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.ResultDTO;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.mapper.UserMapper;
import com.quizapp.userservice.web.jwt.request.RegisterRequest;
import com.quizapp.userservice.web.jwt.request.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final ResultClient resultClient;


    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id: " + userId + " not found"));
        return mapToDTO(user);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email: " + email + " not found"));
        return mapToDTO(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("User with username: " + username + " not found"));
        return mapToDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id: " + id + " not found")
        );
        user.setId(id);

        validateUsername(request.getUsername());
        user.setUsername(request.getUsername());

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
           user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setUserDetails(request.getUserDetails());

        User dbUser = userRepository.save(user);
        return mapToDTO(dbUser);
    }

    @Override
    public UserDTO createUser(RegisterRequest request) {
        validateUsername(request.getUsername());

        User user  = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        User dbUser = userRepository.save(user);
        return mapToDTO(dbUser);
    }



    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id: " + userId + " not found")
        );
        userRepository.delete(user);
    }

    @Override
    public List<ResultDTO> getResultsByUserId(Long userId) {
        return resultClient.getAllByUserId(userId);
    }

    private UserDTO mapToDTO(User user) {
        return userMapper.toDto(user);
    }

    private User mapToEntity(UserDTO userDTO) {
        return userMapper.toEntity(userDTO);
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already exists: " + username);
        }
    }
}
