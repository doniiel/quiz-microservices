package com.quizapp.userservice.service;


import com.quizapp.userservice.web.dto.auth.JwtAuthenticationResponse;
import com.quizapp.userservice.web.dto.auth.SignInRequest;
import com.quizapp.userservice.web.dto.auth.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
