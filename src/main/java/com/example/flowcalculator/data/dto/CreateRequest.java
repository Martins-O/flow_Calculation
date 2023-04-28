package com.example.flowcalculator.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String username;
    private String birthDate;
    private String phoneNumber;
}
