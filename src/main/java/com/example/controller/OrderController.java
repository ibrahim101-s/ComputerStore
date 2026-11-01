package com.example.controller;

import com.example.dto.request.OrderCreateRequestDTO;
import com.example.dto.response.OrderResponseDTO;
import com.example.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Sifarişlər", description = "Səbət ödənişi və sifarişlərin yaradılması APIları")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Yeni sifariş yarat", description = "Səbətdəki məhsulları sifariş edir, stok saylarını azaldır")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderCreateRequestDTO request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}