package com.example.controller;

import com.example.dto.request.ComputerAddRequestDTO;
import com.example.dto.response.ComputerResponseDTO;
import com.example.service.ComputerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
@Tag(name = "Kompüter CRUD", description = "Kompüterlərin əlavə edilməsi, redaktəsi, silinməsi və siyahılanması APIları")
public class ComputerController {

    private final ComputerService computerService;

    @PostMapping
    @Operation(summary = "Yeni kompüter əlavə et", description = "Daxil olmuş istifadəçi tərəfindən yeni kompüter əlavə edilir")
    public ResponseEntity<ComputerResponseDTO> addComputer(@Valid @RequestBody ComputerAddRequestDTO request) {
        return ResponseEntity.ok(computerService.addComputer(request));
    }

    @GetMapping
    @Operation(summary = "Bütün kompüterləri gətir", description = "Alış-veriş səhifəsi üçün stokda olan bütün kompüterləri səhifələmə ilə qaytarır")
    public ResponseEntity<Page<ComputerResponseDTO>> getAllComputers(Pageable pageable) {
        return ResponseEntity.ok(computerService.getAllComputers(pageable));
    }

    @GetMapping("/my")
    @Operation(summary = "Mənim kompüterlərim", description = "daxil olmuş istifadəçinin öz əlavə etdiyi bütün kompüterləri qaytarır")
    public ResponseEntity<List<ComputerResponseDTO>> getMyComputers() {
        return ResponseEntity.ok(computerService.getMyComputers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID-yə görə kompüter tap", description = "Tək bir kompüterin ətraflı məlumatlarını qaytarır")
    public ResponseEntity<ComputerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(computerService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Kompüteri redaktə et", description = "Yalnız kompüterin sahibi tərəfindən məlumatların yenilənməsini təmin edir")
    public ResponseEntity<ComputerResponseDTO> updateComputer(@PathVariable Long id, @Valid @RequestBody ComputerAddRequestDTO request) {
        return ResponseEntity.ok(computerService.updateComputer(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Kompüteri sil", description = "Yalnız kompüterin sahibi tərəfindən kompüterin silinməsini təmin edir")
    public ResponseEntity<Void> deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return ResponseEntity.noContent().build();
    }
}