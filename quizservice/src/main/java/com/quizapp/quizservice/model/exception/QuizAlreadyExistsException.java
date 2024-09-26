package com.quizapp.quizservice.model.exception;

public class QuizAlreadyExistsException extends RuntimeException {
    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
