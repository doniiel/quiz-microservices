package com.quizapp.quizservice.service.impl;


import com.quizapp.quizservice.client.QuestionClient;
import com.quizapp.quizservice.model.entity.Level;
import com.quizapp.quizservice.model.entity.Quiz;
import com.quizapp.quizservice.model.exception.QuizNotFoundException;
import com.quizapp.quizservice.repository.QuizRepository;
import com.quizapp.quizservice.service.QuizService;
import com.quizapp.quizservice.web.dto.QuestionDTO;
import com.quizapp.quizservice.web.dto.QuizDTO;
import com.quizapp.quizservice.web.mapper.QuizMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuestionClient questionClient;
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    @Override
    public QuizDTO getById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz with id: " + id + " not found")
        );
        return quizMapper.mapToDTO(quiz);
    }

    @Override
    public QuizDTO getByTitle(String title) {
        Quiz quiz = quizRepository.findByTitle(title).orElseThrow(
                () -> new QuizNotFoundException("Quiz with title: " + title + " not found")
        );
        return quizMapper.mapToDTO(quiz);
    }

    @Override
    public List<QuizDTO> getAllByLevel(String level) {
        Level levelEnum = validateLevel(level);
        List<Quiz> quizzes = quizRepository.findAllByLevel(levelEnum).orElseThrow(
                () -> new QuizNotFoundException("No quizzes found for level: " + levelEnum)
        );
        return quizzes.stream()
                .map(quizMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizDTO> getCategory(String category) {
        List<Quiz> quizzes = quizRepository.findAllByCategory(category).orElseThrow(
                () ->new QuizNotFoundException("Quiz with category: " + category + " not found")
        );
        return quizzes.stream()
                .map(quizMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuizDTO update(QuizDTO quizDTO) {
        validateLevel(quizDTO.getLevel());
        Quiz existingQuiz = quizRepository.findById(quizDTO.getId())
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id: " + quizDTO.getId() + " not found"));
        Quiz updatedQuiz = quizMapper.mapToEntity(quizDTO);
        updatedQuiz.setId(existingQuiz.getId());

        return quizMapper.mapToDTO(quizRepository.save(updatedQuiz));

    }

    @Override
    @Transactional
    public QuizDTO create(QuizDTO quizDTO) {
        validateLevel(quizDTO.getLevel());
        Quiz newQuiz = quizMapper.mapToEntity(quizDTO);
        return quizMapper.mapToDTO(quizRepository.save(newQuiz));
    }

    @Override
    @Transactional
    public String remove(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new QuizNotFoundException("Quiz with id: " + id + " not found")
        );
        quizRepository.delete(quiz);
        return "Quiz with id: " + id +" deleted";
    }

    /* FEIGN CLIENT METHODS */
    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        return questionClient.createQuestion(questionDTO);
    }

    @Override
    public List<QuestionDTO> getAllByQuizId(Long id) {
        return questionClient.getAllByQuizId(id);
    }

    /* Check for Level field */
    private Level validateLevel(String level) {
        try {
            return Level.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new QuizNotFoundException("Invalid level: " + level);
        }
    }
}
