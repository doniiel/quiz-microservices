package com.quizapp.quizservice.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDTO {

    private Long id;
    private String title;
    private String level;
    private String category;

}
