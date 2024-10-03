package com.quizapp.questionservice.web.controller;

import com.quizapp.questionservice.service.QuestionService;
import com.quizapp.questionservice.web.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/quizapp/question/")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // GET a question by its ID
    // URL: http://localhost:8762/quizapp/question/{id}
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getById(id));
    }

    // GET all questions by quiz ID
    // URL: http://localhost:8762/quizapp/question/quiz/{quizId}
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllByQuizId(@PathVariable("quizId") Long id) {
        return ResponseEntity.ok(questionService.getAllByQuizId(id));
    }

    // GET all questions by category
    // URL: http://localhost:8762/quizapp/question/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionDTO>> getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok(questionService.getAllByCategory(category));
    }

    // GET all questions by level
    // URL: http://localhost:8762/quizapp/question/level/{level}
    @GetMapping("/level/{level}")
    public ResponseEntity<List<QuestionDTO>> getAllByLevel(@PathVariable String level) {
        return ResponseEntity.ok(questionService.getAllByLevel(level));
    }

    // GET all questions filtered by both level and category
    // URL: http://localhost:8762/quizapp/question/filter?level={level}&category={category}
    @GetMapping("/filter")
    public ResponseEntity<List<QuestionDTO>> getAllByCategoryAndLevel(@RequestParam String level,
                                                                      @RequestParam String category) {
        return ResponseEntity.ok(questionService.getAllByCategoryAndLevel(level, category));
    }

    // GET all questions
    // URL: http://localhost:8762/quizapp/question/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<QuestionDTO>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    // PUT request to update a question by ID
    // URL: http://localhost:8762/quizapp/question/{id}
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> update(@PathVariable Long id,
                                              @RequestBody QuestionDTO request) {
        return ResponseEntity.ok(questionService.update(id, request));
    }

    // POST request to create a new question
    // URL: http://localhost:8762/quizapp/question/create
    @PostMapping("/create")
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO request) {
        return new ResponseEntity<>(questionService.create(request), HttpStatus.CREATED);
    }

    // DELETE a question by ID
    // URL: http://localhost:8762/quizapp/question/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long questionId) {
        return ResponseEntity.ok(questionService.remove(questionId));
    }
}


