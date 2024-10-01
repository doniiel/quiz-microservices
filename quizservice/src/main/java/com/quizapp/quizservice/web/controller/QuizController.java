package com.quizapp.quizservice.web.controller;

import com.quizapp.quizservice.client.QuestionClient;
import com.quizapp.quizservice.model.entity.Quiz;
import com.quizapp.quizservice.service.QuizService;
import com.quizapp.quizservice.web.dto.QuestionDTO;
import com.quizapp.quizservice.web.dto.QuestionOption;
import com.quizapp.quizservice.web.dto.QuizDTO;
import jakarta.ws.rs.Path;
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
    public ResponseEntity<QuizDTO> getById(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.getById(quizId));
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

    /* Feign Client API*/
    @PostMapping("/questions")
    public QuestionDTO create(@RequestBody QuestionDTO request) {
        return quizService.createQuestion(request);
    }

    @GetMapping("/quiz/{quizId}")
    public List<QuestionDTO> getAllByQuizId(@PathVariable("quizId") Long id) {
        return quizService.getAllByQuizId(id);
    }

}
