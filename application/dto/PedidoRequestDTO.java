package com.restaurante.tech.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    private List<ItemPedidoRequestDTO> itens;

    private String observacoes;
}