package com.restaurante.tech.application.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;
    private LocalDateTime dataCadastro;
    private Boolean ativo;
}