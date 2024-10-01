package com.quizapp.questionservice.web.dto;

import com.quizapp.questionservice.model.entity.QuestionOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class QuestionDTO {

    private Long id;
    private Long quizId;
    private String question;
    private String category;
    private String level;
    private List<QuestionOption> options;
}
