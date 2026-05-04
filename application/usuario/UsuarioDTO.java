package com.restaurante.tech.application.usuario;

import com.restaurante.tech.domain.usuario.Usuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private Usuario.TipoUsuario tipo;
    private String dataUltimaAlteracao;
}