package com.example.dto.request;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private String phone;
}