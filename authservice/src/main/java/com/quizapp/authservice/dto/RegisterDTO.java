package com.quizapp.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {
    private Long id;
    private String username;
    private String email;

}
