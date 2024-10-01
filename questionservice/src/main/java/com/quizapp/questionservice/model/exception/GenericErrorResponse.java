package com.quizapp.questionservice.model.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class GenericErrorResponse extends RuntimeException{
    private final String message;
    private final HttpStatus status;

    public GenericErrorResponse(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
