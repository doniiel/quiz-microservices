package com.quizapp.questionservice.web.mapper;

import com.quizapp.questionservice.model.enums.Level;
import com.quizapp.questionservice.model.entity.Question;
import com.quizapp.questionservice.web.dto.QuestionDTO;
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
                .options(question.getOptions())
                .build();
    }
    public Question mapToEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .quizId(questionDTO.getQuizId())
                .question(questionDTO.getQuestion())
                .category(questionDTO.getCategory())
                .level(Level.valueOf(questionDTO.getLevel()))
                .options(questionDTO.getOptions())
                .build();
    }

}
