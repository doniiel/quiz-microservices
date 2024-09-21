package com.quizapp.userservice.service.impl;

import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.model.exception.InvalidUserInputException;
import com.quizapp.userservice.model.exception.UserAlreadyExistsException;
import com.quizapp.userservice.model.exception.UserNotFoundException;
import com.quizapp.userservice.repository.UserRepository;
import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.UserCreateRequest;
import com.quizapp.userservice.web.dto.UserDTO;
import com.quizapp.userservice.web.dto.UserUpdateRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserDTO mapToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
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
    public List<UserDTO> getAll() {
        List<User> users= userRepository.findAll();
        List<UserDTO> dtoUsers = new ArrayList<>(users.size());

        for (User user: users) {
            dtoUsers.add(mapToDTO(user));
        }
        return dtoUsers;
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
        User user  = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastname())
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
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
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
}
