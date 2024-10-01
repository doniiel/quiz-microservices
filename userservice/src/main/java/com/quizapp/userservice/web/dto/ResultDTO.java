package com.quizapp.userservice.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO {

    private Long id;
    private Long userId;
    private Long quizId;
    private Integer score;
}
