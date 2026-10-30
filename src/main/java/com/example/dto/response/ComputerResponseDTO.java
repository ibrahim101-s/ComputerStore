package com.example.dto.response;

import lombok.Data;

@Data
public class ComputerResponseDTO {
    private Long id;
    private String brand;
    private String model;
    private Double price;
    private String imageUrl;
    private String storage;
    private String os;
    private String gpu;
    private String ram;
    private String cpu;
    private String username;
    private String phone;
    private String isNew;
    private Integer quantity;
}