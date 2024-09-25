package com.quizapp.questionservice.service.impl;

import com.quizapp.questionservice.model.entity.Question;
import com.quizapp.questionservice.model.exception.QuestionNotFoundException;
import com.quizapp.questionservice.repository.QuestionRepository;
import com.quizapp.questionservice.service.QuestionService;
import com.quizapp.questionservice.web.dto.QuestionDTO;
import com.quizapp.questionservice.web.dto.QuestionMapper;
import com.quizapp.questionservice.web.dto.QuestionRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    @Override
    public QuestionDTO getById(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new QuestionNotFoundException("Question with id: " + questionId + " not found")
        );
        return questionMapper.mapToDTO(question);
    }

    @Override
    public List<QuestionDTO> getAllByQuizId(Long quizId) {
        List<Question> questions = questionRepository.findAllByQuizId(quizId);
        return questions.stream()
                .map(questionMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> getAllByCategory(String category) {
        List<Question> questions = questionRepository.findAllByCategory(category);
        return questions.stream()
                .map(questionMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> getAllByLevel(String level) {
        List<Question> questions = questionRepository.findAllByLevel(level);
        return questions.stream()
                .map(questionMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> getAllByCategoryAndLevel(String category, String level) {
        List<Question> questions = questionRepository.findAllByCategoryAndLevel(category, level);
        return questions.stream()
                .map(questionMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDTO> getAll() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public QuestionDTO update(QuestionRequest request) {
        Question question = questionMapper.mapToEntity(request);
        questionRepository.save(question);
        return questionMapper.mapToDTO(question);
    }

    @Override
    @Transactional
    public QuestionDTO create(QuestionRequest request) {
        Question question = questionMapper.mapToEntity(request);
        questionRepository.save(question);
        return questionMapper.mapToDTO(question);
    }

    @Override
    public String remove(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(
                () -> new QuestionNotFoundException("Question with id: " + questionId + " not found")
        );
        return "Question with id: " + questionId + " deleted.";
    }
}
