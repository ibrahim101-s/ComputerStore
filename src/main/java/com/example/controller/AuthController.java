package com.example.controller;

import com.example.dto.request.AuthRequestDTO;
import com.example.dto.response.AuthResponseDTO;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autentifikasiya", description = "İstifadəçi qeydiyyatı və sistemə daxilolma APIları")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Sistemə daxil ol", description = "İstifadəçi adı və şifrə ilə sistemə daxil olaraq JWT token əldə edir")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    @Operation(summary = "Yeni istifadəçi qeydiyyatı", description = "Yeni hesab yaradır")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }
}