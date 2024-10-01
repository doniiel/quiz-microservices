package com.quizapp.quizservice.web.dto;

import lombok.Data;

@Data
public class QuestionOption {
    private String optionText;
    private Integer correct;
}
