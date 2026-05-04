package com.restaurante.tech.application.usuario;

import com.restaurante.tech.domain.usuario.Usuario;
import java.time.format.DateTimeFormatter;

public class UsuarioMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .tipo(usuario.getTipo())
                .dataUltimaAlteracao(usuario.getDataUltimaAlteracao().format(FORMATTER))
                .build();
    }

    public static Usuario toEntity(UsuarioCadastroDTO dto) {
        return Usuario.builder()
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .build();
    }
}