package com.example.dto.request;

import lombok.Data;

@Data
public class ComputerAddRequestDTO {
    private String brand;
    private String model;
    private Double price;
    private String imageUrl;
    private String storage;
    private String os;
    private String gpu;
    private String ram;
    private String cpu;
    private String isNew;
    private Integer quantity;
}