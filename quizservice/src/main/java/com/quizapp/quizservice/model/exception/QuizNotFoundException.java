package com.quizapp.quizservice.model.exception;

import com.quizapp.quizservice.model.entity.Quiz;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException(String message) {
        super(message);
    }
}
