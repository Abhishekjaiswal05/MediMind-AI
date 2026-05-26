package com.medimind.medimindbackend.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;

    // NEW ROLE FIELD
    private String role;
}