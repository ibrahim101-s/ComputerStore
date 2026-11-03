package com.example.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequestDTO {

    @NotBlank(message = "Ünvan boş ola bilməz")
    @Size(min = 5, max = 150, message = "Ünvan 5-150 simvol arasında olmalıdır")
    private String address;

    @NotBlank(message = "Kart nömrəsi boş ola bilməz")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Kart nömrəsi 19 simvol olmalıdır (1234 5678 1234 5678)")
    private String cardNumber;

    @NotEmpty(message = "Səbət boş ola bilməz")
    @Valid
    private List<OrderItemRequestDTO> items;
}