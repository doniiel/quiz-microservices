package com.quizapp.authservice.client;

import com.quizapp.authservice.dto.RegisterDTO;
import com.quizapp.authservice.dto.UserDTO;
import com.quizapp.authservice.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url= "${application.config.user-url}")
public interface UserServiceClient {

    @PostMapping("/create")
    ResponseEntity<RegisterDTO> createUser(@RequestBody RegisterRequest request);

    @GetMapping("/byUsername/{username}")
    ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username);

}
