package com.quizapp.questionservice.service.impl;

import com.quizapp.questionservice.model.enums.Level;
import com.quizapp.questionservice.model.entity.Question;
import com.quizapp.questionservice.model.entity.QuestionOption;
import com.quizapp.questionservice.model.exception.InvalidInputException;
import com.quizapp.questionservice.model.exception.QuestionNotFoundException;
import com.quizapp.questionservice.repository.QuestionRepository;
import com.quizapp.questionservice.service.QuestionService;
import com.quizapp.questionservice.web.dto.QuestionDTO;
import com.quizapp.questionservice.web.mapper.QuestionMapper;
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
    public QuestionDTO update(QuestionDTO request) {
        Question exist = questionRepository.findById(request.getId()).orElseThrow(
                () -> new QuestionNotFoundException("Question not found with id: " + request.getId())
        );

        if (request.getQuestion() == null || request.getOptions().size() != 4) {
            throw new InvalidInputException("A question must have exactly 4 options");
        }
        boolean hasCorrectOption = request.getOptions().stream()
                .anyMatch(option -> option.getCorrect() == 1);
        if (!hasCorrectOption) {
            throw new InvalidInputException("At least one option must be marked as correct");
        }
        exist.setQuestion(request.getQuestion());
        exist.setCategory(request.getCategory());
        exist.setLevel(Level.valueOf(request.getLevel()));

        exist.getOptions().clear();
        for (QuestionOption option:  request.getOptions()) {
            QuestionOption questionOption = new QuestionOption();
            questionOption.setCorrect(option.getCorrect());
            questionOption.setOptionText(option.getOptionText());
            questionOption.setQuestion(exist);
            exist.getOptions().add(questionOption);
        }

        questionRepository.save(exist);
        return questionMapper.mapToDTO(exist);
    }

    @Override
    @Transactional
    public QuestionDTO create(QuestionDTO request) {
        if (request.getOptions() == null || request.getOptions().size() != 4) {
            throw new InvalidInputException("A question must have exactly 4 options.");
        }

        boolean hasCorrectOption = request.getOptions().stream()
                .anyMatch(option -> option.getCorrect() == 1);
        if (!hasCorrectOption) {
            throw new InvalidInputException("At least one option must be marked as correct.");
        }

        Question question = questionMapper.mapToEntity(request);

        if (request.getOptions() != null) {
            for (QuestionOption option : request.getOptions()) {
                option.setQuestion(question);
            }
        }

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
