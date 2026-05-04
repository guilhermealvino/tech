package com.restaurante.tech.application.usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioTrocaSenhaDTO {
    private String novaSenha;
}