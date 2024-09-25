package com.quizapp.questionservice.web.dto;

import com.quizapp.questionservice.model.entity.Level;
import com.quizapp.questionservice.model.entity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public QuestionDTO mapToDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .quizId(question.getQuizId())
                .question(question.getQuestion())
                .category(question.getCategory())
                .level(question.getLevel().name())
                .build();
    }
    public Question mapToEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .question(questionDTO.getQuestion())
                .category(questionDTO.getCategory())
                .level(Level.valueOf(questionDTO.getLevel()))
                .build();
    }

    public Question mapToEntity(QuestionRequest request) {
        return Question.builder()
                .question(request.getQuestion())
                .quizId(request.getQuizId())
                .category(request.getCategory())
                .level(Level.valueOf(request.getLevel()))
                .build();
    }
}
