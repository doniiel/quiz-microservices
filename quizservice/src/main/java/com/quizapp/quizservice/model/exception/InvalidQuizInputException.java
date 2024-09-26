package com.quizapp.quizservice.model.exception;

public class InvalidQuizInputException extends RuntimeException {
    public InvalidQuizInputException(String message) {
        super(message);
    }
}
