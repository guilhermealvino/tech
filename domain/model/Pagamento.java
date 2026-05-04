package com.restaurante.tech.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false, unique = true)
    private Pedido pedido;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status = StatusPagamento.PENDENTE;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "codigo_transacao")
    private String codigoTransacao;

    public enum FormaPagamento {
        DINHEIRO,
        CARTAO_CREDITO,
        CARTAO_DEBITO,
        PIX
    }

    public enum StatusPagamento {
        PENDENTE,
        APROVADO,
        RECUSADO,
        ESTORNADO
    }

    public void confirmarPagamento(String codigoTransacao) {
        this.status = StatusPagamento.APROVADO;
        this.dataPagamento = LocalDateTime.now();
        this.codigoTransacao = codigoTransacao;
    }
}