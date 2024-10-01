package com.quizapp.quizservice.web.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionRequest {

    private Long quizId;
    private String question;
    private String category;
    private String level;
}
