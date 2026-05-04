package com.restaurante.tech.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PagamentoResponseDTO {
    private Long id;
    private Long pedidoId;
    private String formaPagamento;
    private String status;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String codigoTransacao;
}