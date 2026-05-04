package com.restaurante.tech.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemPedidoResponseDTO {
    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}