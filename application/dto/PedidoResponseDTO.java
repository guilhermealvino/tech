package com.restaurante.tech.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private Long clienteId;
    private String clienteNome;
    private List<ItemPedidoResponseDTO> itens;
    private String status;
    private BigDecimal valorTotal;
    private LocalDateTime dataPedido;
    private String observacoes;
}