package com.example.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class OrderCreateRequestDTO {

    @NotEmpty(message = "Sifariş üçün ən azı bir məhsul seçilməlidir")
    @Valid
    private List<OrderItemRequestDTO> items;
}