package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "computers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String isNew;
    private String username;
    private String phone;
}