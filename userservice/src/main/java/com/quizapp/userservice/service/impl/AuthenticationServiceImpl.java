package com.quizapp.userservice.service.impl;

import com.quizapp.userservice.model.entity.Role;
import com.quizapp.userservice.model.entity.User;
import com.quizapp.userservice.service.AuthenticationService;
import com.quizapp.userservice.service.JwtService;
import com.quizapp.userservice.service.UserService;
import com.quizapp.userservice.web.dto.auth.JwtAuthenticationResponse;
import com.quizapp.userservice.web.dto.auth.SignInRequest;
import com.quizapp.userservice.web.dto.auth.SignUpRequest;
import com.quizapp.userservice.web.dto.user.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .confirmationPassword(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                                    .build();

        userService.create(userCreateRequest);
        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
