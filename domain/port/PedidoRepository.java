package com.restaurante.tech.domain.port;

import com.restaurante.tech.domain.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<Pedido> findAll();
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByStatus(Pedido.StatusPedido status);
    void deleteById(Long id);
}