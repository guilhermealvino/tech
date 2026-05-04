package com.restaurante.tech.application.usuario;

import com.restaurante.tech.domain.usuario.Usuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCadastroDTO {
    private String email;
    private String senha;
    private String nome;
    private Usuario.TipoUsuario tipo;
}