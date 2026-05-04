package com.restaurante.tech.adapter.outbound.repository;

import com.restaurante.tech.domain.model.Pedido;
import com.restaurante.tech.domain.port.PedidoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoJpaRepository extends PedidoRepository, JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    List<Pedido> findByStatus(Pedido.StatusPedido status);
}