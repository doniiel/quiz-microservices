package com.quizapp.userservice.client;

import com.quizapp.userservice.web.dto.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "result-service", url = "${application.config.result-url}")
public interface ResultClient {

    @GetMapping("/results/{id}")
    public List<ResultDTO> getAllByUserId(@PathVariable("id") Long userId);
}
