package com.quizapp.resultservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

    private Long id;
    private Long userId;
    private Long quizId;
    private Integer score;
}
