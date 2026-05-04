package com.restaurante.tech.application.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class TokenRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String scope = "api";
}
