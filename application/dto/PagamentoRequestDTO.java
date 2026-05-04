package com.restaurante.tech.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagamentoRequestDTO {

    @NotNull(message = "Pedido é obrigatório")
    private Long pedidoId;

    @NotNull(message = "Forma de pagamento é obrigatória")
    private String formaPagamento;
}