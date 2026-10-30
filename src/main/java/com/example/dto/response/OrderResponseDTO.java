package com.example.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private String username;
    private Double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDTO> items;

    @Data
    public static class OrderItemResponseDTO {
        private Long computerId;
        private String computerName;
        private Double price;
        private Integer quantity;
    }
}