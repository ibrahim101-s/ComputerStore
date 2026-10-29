package com.example.service;

import com.example.dto.request.AuthRequestDTO;
import com.example.dto.response.AuthResponseDTO;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import com.example.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public AuthResponseDTO login(AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponseDTO(token, request.getUsername());
    }

    public AuthResponseDTO register(AuthRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Bu istifadəçi adı artıq mövcuddur!");
        }

        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Bu telefon nömrəsi artıq qeydiyyatdan keçib!");
        }

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token, user.getUsername());

    }

}