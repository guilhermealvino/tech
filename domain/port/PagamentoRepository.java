package com.restaurante.tech.domain.port;

import com.restaurante.tech.domain.model.Pagamento;

import java.util.Optional;

public interface PagamentoRepository {
    Pagamento save(Pagamento pagamento);
    Optional<Pagamento> findById(Long id);
    Optional<Pagamento> findByPedidoId(Long pedidoId);
    void deleteById(Long id);
}