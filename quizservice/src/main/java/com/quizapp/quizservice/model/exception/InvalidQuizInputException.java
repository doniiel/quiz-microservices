package com.quizapp.quizservice.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuizInputException extends RuntimeException {
    public InvalidQuizInputException(String message) {
        super(message);
    }
}
