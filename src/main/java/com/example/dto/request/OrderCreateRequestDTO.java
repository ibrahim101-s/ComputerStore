package com.example.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class OrderCreateRequestDTO {
    private List<OrderItemRequestDTO> items;
}