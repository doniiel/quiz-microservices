package com.quizapp.quizservice.web.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDTO {

    private Long id;
    private Long quizId;
    private String question;
    private String category;
    private String level;
    private List<QuestionOption> options;
}
