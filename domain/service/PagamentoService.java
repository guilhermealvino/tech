package com.restaurante.tech.domain.service;

import com.restaurante.tech.domain.model.Pagamento;
import com.restaurante.tech.domain.model.Pedido;
import com.restaurante.tech.domain.port.PagamentoRepository;
import com.restaurante.tech.domain.port.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public Pagamento processarPagamento(Pagamento pagamento) {
        Pedido pedido = pedidoRepository.findById(pagamento.getPedido().getId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pagamento.setPedido(pedido);
        pagamento.setValor(pedido.getValorTotal());
        
        // Simulação de processamento de pagamento
        String codigoTransacao = UUID.randomUUID().toString();
        pagamento.confirmarPagamento(codigoTransacao);

        return pagamentoRepository.save(pagamento);
    }

    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

    public Optional<Pagamento> buscarPorPedido(Long pedidoId) {
        return pagamentoRepository.findByPedidoId(pedidoId);
    }

    public Pagamento estornarPagamento(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        
        pagamento.setStatus(Pagamento.StatusPagamento.ESTORNADO);
        return pagamentoRepository.save(pagamento);
    }
}