package com.quizapp.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetails {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private String postalCode;
    private String aboutMe;
    private String profilePicture;
}
