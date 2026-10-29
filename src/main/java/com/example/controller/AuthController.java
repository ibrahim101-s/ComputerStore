package com.example.controller;

import com.example.dto.request.AuthRequestDTO;
import com.example.dto.response.AuthResponseDTO;
import com.example.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
}