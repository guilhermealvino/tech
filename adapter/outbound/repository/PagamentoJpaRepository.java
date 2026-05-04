package com.restaurante.tech.adapter.outbound.repository;

import com.restaurante.tech.domain.model.Pagamento;
import com.restaurante.tech.domain.port.PagamentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoJpaRepository extends PagamentoRepository, JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByPedidoId(Long pedidoId);
}