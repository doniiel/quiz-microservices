package com.quizapp.quizservice.repository;

import com.quizapp.quizservice.model.entity.Level;
import com.quizapp.quizservice.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByTitle(String title);
    Optional<List<Quiz>> findAllByLevel(Level level);
    Optional<List<Quiz>> findAllByCategory(String category);


}
