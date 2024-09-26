package com.quizapp.resultservice.web.dto;

import com.quizapp.resultservice.model.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {
    public Result mapToEntity(ResultDTO resultDTO) {
        return Result.builder()
                .id(resultDTO.getId())
                .userId(resultDTO.getUserId())
                .quizId(resultDTO.getQuizId())
                .score(resultDTO.getScore())
                .build();
    }

    public ResultDTO mapToDto(Result result) {
        return ResultDTO.builder()
                .id(result.getId())
                .userId(result.getUserId())
                .quizId(result.getQuizId())
                .score(result.getScore())
                .build();
    }
}
