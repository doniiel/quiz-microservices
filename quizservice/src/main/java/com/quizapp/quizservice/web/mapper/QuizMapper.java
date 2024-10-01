package com.quizapp.quizservice.web.mapper;

import com.quizapp.quizservice.model.entity.Level;
import com.quizapp.quizservice.model.entity.Quiz;
import com.quizapp.quizservice.web.dto.QuizDTO;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public QuizDTO mapToDTO(Quiz quiz) {
        return QuizDTO.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .level(quiz.getTitle())
                .category(quiz.getCategory())
                .build();
    }

    public Quiz mapToEntity(QuizDTO quizDTO) {
        return Quiz.builder()
                .id(quizDTO.getId())
                .title(quizDTO.getTitle())
                .level(Level.valueOf(quizDTO.getLevel()))
                .category(quizDTO.getCategory())
                .build();
    }
}
