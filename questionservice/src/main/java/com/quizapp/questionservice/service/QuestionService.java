package com.quizapp.questionservice.service;

import com.quizapp.questionservice.web.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {
    QuestionDTO getById(Long questionId);
    List<QuestionDTO> getAllByQuizId(Long quizId);
    List<QuestionDTO> getAllByCategory(String category);
    List<QuestionDTO> getAllByLevel(String level);
    List<QuestionDTO> getAllByCategoryAndLevel(String category, String level);
    List<QuestionDTO> getAll();
    QuestionDTO update(QuestionDTO request);
    QuestionDTO create(QuestionDTO request);
    String remove(Long questionId);



}
