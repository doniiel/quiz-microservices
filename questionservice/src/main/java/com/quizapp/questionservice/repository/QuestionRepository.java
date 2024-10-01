package com.quizapp.questionservice.repository;

import com.quizapp.questionservice.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByQuizId(Long quizId);
    List<Question> findAllByCategory(String category);
    List<Question> findAllByLevel(String level);
    List<Question> findAllByCategoryAndLevel(String category, String level);
}
