package com.quizapp.userservice.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.quizapp.userservice.model.entity.UserDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private UserDetails userDetails;

}
