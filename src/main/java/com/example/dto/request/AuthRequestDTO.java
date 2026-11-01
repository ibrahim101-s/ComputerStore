package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotBlank(message = "İstifadəçi adı boş ola bilməz")
    @Size(min = 2, max = 30, message = "İstifadəçi adı 2-30 simvol arasında olmalıdır")
    private String username;

    @NotBlank(message = "Şifrə boş ola bilməz")
    @Size(min = 2, max = 30, message = "Şifrə 2-30 simvol arasında olmalıdır")
    private String password;

    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Telefon nömrəsi xxx-xxx-xxxx formatında olmalıdır")
    private String phone;
}