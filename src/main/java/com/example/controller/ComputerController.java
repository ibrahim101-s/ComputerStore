package com.example.controller;

import com.example.dto.request.ComputerAddRequestDTO;
import com.example.dto.response.ComputerResponseDTO;
import com.example.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @PostMapping
    public ResponseEntity<ComputerResponseDTO> addComputer(@RequestBody ComputerAddRequestDTO request) {
        return ResponseEntity.ok(computerService.addComputer(request));
    }

    @GetMapping
    public ResponseEntity<Page<ComputerResponseDTO>> getAllComputers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return ResponseEntity.ok(computerService.getAllComputers(PageRequest.of(page, size)));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ComputerResponseDTO>> getMyComputers() {
        return ResponseEntity.ok(computerService.getMyComputers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(computerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerResponseDTO> updateComputer(@PathVariable Long id, @RequestBody ComputerAddRequestDTO request) {
        return ResponseEntity.ok(computerService.updateComputer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }
}