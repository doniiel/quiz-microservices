package com.quizapp.resultservice.web.controller;

import com.quizapp.resultservice.service.ResultService;
import com.quizapp.resultservice.web.dto.ResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/quizapp/result")
public class ResultController {

    private final ResultService resultService;

    // Get result by ID
    // URL: http://localhost:8762/quizapp/result/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getById(@PathVariable("id") Long resultId) {
        return ResponseEntity.ok(resultService.getById(resultId));
    }

    // Get all results
    // URL: http://localhost:8762/quizapp/result/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<ResultDTO>> getAll() {
        return ResponseEntity.ok(resultService.getAll());
    }

    // Get all results by user ID
    // URL: http://localhost:8762/quizapp/result/results/{id}
    @GetMapping("/results/{id}")
    public ResponseEntity<List<ResultDTO>> getAllByUserId(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(resultService.getAllByUserId(userId));
    }

    // Update an existing result
    // URL: http://localhost:8762/quizapp/result/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> update(@PathVariable Long id,@RequestBody  ResultDTO resultDTO) {
        return ResponseEntity.ok(resultService.update(id, resultDTO));
    }

    // Create a new result
    // URL: http://localhost:8762/quizapp/result/create
    @PostMapping("/create")
    public ResponseEntity<ResultDTO> create(@RequestBody  ResultDTO resultDTO) {
        return ResponseEntity.ok(resultService.create(resultDTO));
    }

    // Delete a result by ID
    // URL: http://localhost:8762/quizapp/result/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long resultId) {
        return new ResponseEntity<>(resultService.remove(resultId), HttpStatus.OK);
    }
}
