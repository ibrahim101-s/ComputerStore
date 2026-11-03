package com.example.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDTO {

    @NotNull(message = "Kompüter ID-si daxil edilməlidir")
    private Long computerId;

    @NotNull(message = "Sayı daxil edilməlidir")
    @Min(value = 1, message = "Minimum 1 ədəd sifariş edilə bilər")
    private Integer quantity;
}