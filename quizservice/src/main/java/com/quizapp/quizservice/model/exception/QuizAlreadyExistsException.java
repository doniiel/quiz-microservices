package com.quizapp.quizservice.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class QuizAlreadyExistsException extends RuntimeException {
    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
