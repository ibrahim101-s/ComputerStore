package com.example.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDTO {

    @NotBlank(message = "Ad boş ola bilməz")
    @Size(min = 2, max = 50, message = "Ad 2 ilə 50 simvol arasında olmalıdır")
    private String name;

    @NotBlank(message = "İstifadəçi adı boş ola bilməz")
    @Size(min = 2, max = 30, message = "İstifadəçi adı 2 ilə 30 simvol arasında olmalıdır")
    private String username;

    @NotBlank(message = "Şifrə boş ola bilməz")
    @Size(min = 2, max = 30, message = "Şifrə 2 ilə 30 simvol arasında olmalıdır")
    private String password;

    @NotBlank(message = "Telefon nömrəsi boş ola bilməz")
    @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Telefon nömrəsi düzgün formatda deyil (məs: 000-000-0000)")
    private String phone;
}