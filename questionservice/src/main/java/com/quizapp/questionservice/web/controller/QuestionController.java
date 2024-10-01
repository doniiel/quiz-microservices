package com.quizapp.questionservice.web.controller;

import com.quizapp.questionservice.service.QuestionService;
import com.quizapp.questionservice.web.dto.QuestionDTO;
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

    @GetMapping("/quiz/{quizId}")
    public List<QuestionDTO> getAllByQuizId(@PathVariable("quizId") Long id) {
        return questionService.getAllByQuizId(id);
    }

    @GetMapping("/catagory/{name}")
    public List<QuestionDTO> getAllByCategory(@PathVariable("name") String category) {
        return questionService.getAllByCategory(category);
    }

    @GetMapping("/level/{name}")
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
    public QuestionDTO update(@RequestBody QuestionDTO request) {
        return questionService.update(request);
    }

    @PostMapping("/questions")
    public QuestionDTO create(@RequestBody QuestionDTO request) {
        return questionService.create(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long questionId) {
        return ResponseEntity.ok(questionService.remove(questionId));
    }



}
