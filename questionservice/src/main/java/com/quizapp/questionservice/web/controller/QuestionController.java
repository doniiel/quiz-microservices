package com.quizapp.questionservice.web.controller;

import com.quizapp.questionservice.service.QuestionService;
import com.quizapp.questionservice.web.dto.QuestionDTO;
import com.quizapp.questionservice.web.dto.QuestionRequest;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quizapp/question/")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionDTO getById(@PathVariable("id") Long questionId) {
        return questionService.getById(questionId);
    }

    @GetMapping("/qnum/{id}")
    public List<QuestionDTO> getAllByQuizId(@PathVariable("id") Long quizId) {
        return questionService.getAllByQuizId(quizId);
    }

    @GetMapping("/cat/{name}")
    public List<QuestionDTO> getAllByCategory(@PathVariable("name") String category) {
        return questionService.getAllByCategory(category);
    }

    @GetMapping("/lvl/{name}")
    public List<QuestionDTO> getAllByQuestion(@PathVariable("name") String level) {
        return questionService.getAllByLevel(level);
    }

    @GetMapping("/lvl&cat")
    public List<QuestionDTO> getAllByCategoryAndLevel(@RequestParam String level, @RequestParam String category) {
        return questionService.getAllByCategoryAndLevel(level, category);
    }

    @GetMapping("")
    public List<QuestionDTO> getAll() {
        return questionService.getAll();
    }

    @PutMapping("")
    public QuestionDTO update(@RequestBody QuestionRequest questionRequest) {
        return questionService.update(questionRequest);
    }

    @PostMapping("")
    public QuestionDTO create(@RequestBody QuestionRequest questionRequest) {
        return questionService.create(questionRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long questionId) {
        return ResponseEntity.ok(questionService.remove(questionId));
    }



}
