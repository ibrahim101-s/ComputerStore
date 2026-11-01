package com.example.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequestDTO {

    @NotNull(message = "Kompüter ID-si boş ola bilməz")
    private Long computerId;

    @NotNull(message = "Miqdar daxil edilməlidir")
    @Min(value = 1, message = "Miqdar minimum 1 olmalıdır")
    private Integer quantity;
}