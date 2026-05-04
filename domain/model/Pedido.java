package com.restaurante.tech.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ABERTO;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_pedido", nullable = false, updatable = false)
    private LocalDateTime dataPedido;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(length = 1000)
    private String observacoes;

    @PrePersist
    protected void onCreate() {
        dataPedido = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    public void calcularTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public enum StatusPedido {
        ABERTO,
        CONFIRMADO,
        EM_PREPARACAO,
        PRONTO,
        EM_ENTREGA,
        ENTREGUE,
        CANCELADO
    }
}