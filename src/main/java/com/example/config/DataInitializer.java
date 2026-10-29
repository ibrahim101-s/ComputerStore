package com.example.config;

import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("u1")) {
            UserEntity defaultUser = UserEntity.builder()
                    .username("u1")
                    .password(passwordEncoder.encode("p1"))
                    .build();
            userRepository.save(defaultUser);
        }
    }
}