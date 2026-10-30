package com.example.dto.request;

import lombok.Data;

@Data
public class OrderItemRequestDTO {
    private Long computerId;
    private Integer quantity;
}