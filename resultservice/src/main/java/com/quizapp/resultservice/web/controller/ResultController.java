package com.quizapp.resultservice.web.controller;

import com.quizapp.resultservice.service.ResultService;
import com.quizapp.resultservice.web.dto.ResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizapp/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @GetMapping("/{id}")
    public ResultDTO getById(@PathVariable("id") Long resultId) {
        return resultService.getById(resultId);
    }

    @GetMapping("")
    public List<ResultDTO> getAll() {
        return resultService.getAll();
    }

    @GetMapping("/results/{id}")
    public List<ResultDTO> getAllByUserId(@PathVariable("id") Long userId) {
        return resultService.getAllByUserId(userId);
    }

    @PutMapping("")
    public ResultDTO update(@RequestBody  ResultDTO resultDTO) {
        return resultService.update(resultDTO);
    }

    @PostMapping("")
    public ResultDTO create(@RequestBody  ResultDTO resultDTO) {
        return resultService.create(resultDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long resultId) {
        return new ResponseEntity<>(resultService.remove(resultId), HttpStatus.OK);
    }
}
