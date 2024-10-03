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
@CrossOrigin("*")
@RequestMapping("/quizapp/quizzes/")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // GET a quiz by its ID
    // URL: http://localhost:8762/quizapp/quizzes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getById(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.getById(quizId));
    }


    // GET a quiz by its title
    // URL: http://localhost:8762/quizapp/quizzes/title/{title}
    @GetMapping("/title/{title}")
    public ResponseEntity<QuizDTO> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(quizService.getByTitle(title));
    }

    // GET all quizzes by level
    // URL: http://localhost:8762/quizapp/quizzes/level/{level}
    @GetMapping("/level/{level}")
    public ResponseEntity<List<QuizDTO>> getAllByLevel(@PathVariable String level) {
        return ResponseEntity.ok(quizService.getAllByLevel(level));
    }

    // GET all quizzes by category
    // URL: http://localhost:8762/quizapp/quizzes/category/{category}
    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuizDTO>> getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok(quizService.getCategory(category));
    }

    // PUT request to update a quiz by ID
    // URL: http://localhost:8762/quizapp/quizzes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<QuizDTO> update(@PathVariable Long id,@RequestBody QuizDTO quizDTO) {
        quizDTO.setId(id);
        return ResponseEntity.ok(quizService.update(quizDTO));
    }

    // POST request to create a new quiz
    // URL: http://localhost:8762/quizapp/quizzes
    @PostMapping
    public ResponseEntity<QuizDTO> create(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.create(quizDTO), HttpStatus.CREATED);
    }


    // DELETE a quiz by ID
    // URL: http://localhost:8762/quizapp/quizzes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.remove(quizId));
    }

    /* Feign Client API*/

    // POST request to create a question for a quiz
    // URL: http://localhost:8762/quizapp/quizzes/create
    @PostMapping("/create")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO request) {
        return new ResponseEntity<>(quizService.createQuestion(request), HttpStatus.CREATED);
    }

    // GET all questions by quiz ID using Feign Client
    // URL: http://localhost:8762/quizapp/quizzes/quiz/{quizId}
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllByQuizId(@PathVariable("quizId") Long id) {
        return ResponseEntity.ok(quizService.getAllByQuizId(id));
    }

}
