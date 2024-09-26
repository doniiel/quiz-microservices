package com.quizapp.resultservice.repository;

import com.quizapp.resultservice.model.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
