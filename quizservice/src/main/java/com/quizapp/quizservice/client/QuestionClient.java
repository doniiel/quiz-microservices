package com.quizapp.quizservice.client;


import com.quizapp.quizservice.web.dto.QuestionDTO;
import com.quizapp.quizservice.web.request.QuestionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "question-server", url = "${application.config.question-url}")
public interface QuestionClient {

    @GetMapping("/quiz/{quizId}")
    List<QuestionDTO> getAllByQuizId(@PathVariable("quizId") Long id);

    @PostMapping("/create")
    QuestionDTO createQuestion(@RequestBody QuestionDTO request);
}
