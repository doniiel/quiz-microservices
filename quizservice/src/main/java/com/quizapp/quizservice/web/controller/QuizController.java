package com.quizapp.quizservice.web.controller;

import com.quizapp.quizservice.model.entity.Quiz;
import com.quizapp.quizservice.service.QuizService;
import com.quizapp.quizservice.web.dto.QuizDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.Remove;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quizapp/quiz/")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/{id}")
    public QuizDTO getById(@PathVariable("id") Long quizId) {
        return quizService.getById(quizId);
    }

    @GetMapping("/title/{str}")
    public QuizDTO getByTitle(@PathVariable("str") String title) {
        return quizService.getByTitle(title);
    }

    @GetMapping("/level/{lvl}")
    public List<QuizDTO> getAllByLevel(@PathVariable("lvl") String level) {
        return quizService.getAllByLevel(level);
    }

    @GetMapping("/category/{cat}")
    public List<QuizDTO> getAllByCategory(@PathVariable("cat") String category) {
        return quizService.getCategory(category);
    }

    @PutMapping("")
    public QuizDTO update(@RequestBody QuizDTO quizDTO) {
        return quizService.update(quizDTO);
    }

    @PostMapping("")
    public QuizDTO create(@RequestBody QuizDTO quizDTO) {
        return quizService.create(quizDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long quizId) {
        return new ResponseEntity<>(quizService.remove(quizId), HttpStatus.OK);
    }
}
