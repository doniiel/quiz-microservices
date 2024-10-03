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

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<QuizDTO> getById(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.getById(quizId));
    }

    // get by title
    @GetMapping("/title/{title}")
    public ResponseEntity<QuizDTO> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(quizService.getByTitle(title));
    }

    // get by title
    @GetMapping("/level/{level}")
    public ResponseEntity<List<QuizDTO>> getAllByLevel(@PathVariable String level) {
        return ResponseEntity.ok(quizService.getAllByLevel(level));
    }

    // get by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuizDTO>> getAllByCategory(@PathVariable String category) {
        return ResponseEntity.ok(quizService.getCategory(category));
    }

    // update by id
    @PutMapping("/{id}")
    public ResponseEntity<QuizDTO> update(@PathVariable Long id,@RequestBody QuizDTO quizDTO) {
        quizDTO.setId(id);
        return ResponseEntity.ok(quizService.update(quizDTO));
    }

    // create quiz
    @PostMapping
    public ResponseEntity<QuizDTO> create(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(quizService.create(quizDTO), HttpStatus.CREATED);
    }


    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.remove(quizId));
    }

    /* Feign Client API*/

    // create question
    @PostMapping("/create")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO request) {
        return new ResponseEntity<>(quizService.createQuestion(request), HttpStatus.CREATED);
    }

    // get question by quiz_id
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllByQuizId(@PathVariable("quizId") Long id) {
        return ResponseEntity.ok(quizService.getAllByQuizId(id));
    }

}
