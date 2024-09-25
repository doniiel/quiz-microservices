package com.quizapp.questionservice.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionDTO {
    private Long id;
    private Long quizId;
    private String question;
    private String category;
    private String level;
}
