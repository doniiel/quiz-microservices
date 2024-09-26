package com.quizapp.resultservice.model.exception;

public class InvalidResultException extends RuntimeException {
    public InvalidResultException(String message) {
        super(message);
    }
}
