package com.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ComputerAddRequestDTO {

    @NotBlank(message = "Kateqoriya boş ola bilməz")
    private String brand;

    @NotBlank(message = "Model adı boş ola bilməz")
    private String model;

    @NotNull(message = "Qiymət daxil edilməlidir")
    @DecimalMin(value = "0.0", inclusive = true, message = "Qiymət mənfi ola bilməz")
    private Double price;

    @NotBlank(message = "Şəkil URL-i boş ola bilməz")
    private String imageUrl;

    private String description;

    @NotBlank(message = "RAM boş ola bilməz")
    private String ram;

    @NotBlank(message = "CPU boş ola bilməz")
    private String cpu;

    @NotBlank(message = "Daimi yaddaş boş ola bilməz")
    private String storage;

    @NotBlank(message = "Əməliyyat sistemi boş ola bilməz")
    private String os;

    @NotBlank(message = "Video kart boş ola bilməz")
    private String gpu;

    @NotBlank(message = "Yeni (Bəli/Xeyr) seçimi boş ola bilməz")
    private String isNew;

    @NotNull(message = "Stok sayı daxil edilməlidir")
    @Min(value = 1, message = "Stok sayı minimum 1 olmalıdır")
    private Integer quantity;
}