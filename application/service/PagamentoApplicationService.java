package com.restaurante.tech.application.service;

import com.restaurante.tech.application.dto.*;
import com.restaurante.tech.application.mapper.PagamentoMapper;
import com.restaurante.tech.domain.model.Pagamento;
import com.restaurante.tech.domain.model.Pedido;
import com.restaurante.tech.domain.service.PagamentoService;
import com.restaurante.tech.domain.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PagamentoApplicationService {

    private final PagamentoService pagamentoService;
    private final PedidoService pedidoService;
    private final PagamentoMapper pagamentoMapper;

    @Transactional
    public PagamentoResponseDTO processar(PagamentoRequestDTO dto) {
        Pedido pedido = pedidoService.buscarPorId(dto.getPedidoId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido sem itens");
        }

        Pagamento pagamento = pagamentoMapper.toEntity(dto, pedido);
        Pagamento pagamentoSalvo = pagamentoService.processarPagamento(pagamento);

        pedidoService.atualizarStatus(pedido.getId(), Pedido.StatusPedido.CONFIRMADO);
        
        return pagamentoMapper.toResponse(pagamentoSalvo);
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        return pagamentoService.buscarPorId(id)
                .map(pagamentoMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
    }

    public PagamentoResponseDTO buscarPorPedido(Long pedidoId) {
        return pagamentoService.buscarPorPedido(pedidoId)
                .map(pagamentoMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
    }

    public PagamentoResponseDTO estornar(Long id) {
        Pagamento pagamento = pagamentoService.estornarPagamento(id);
        return pagamentoMapper.toResponse(pagamento);
    }
}