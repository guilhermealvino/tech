package com.restaurante.tech.application.mapper;

import com.restaurante.tech.application.dto.PagamentoRequestDTO;
import com.restaurante.tech.application.dto.PagamentoResponseDTO;
import com.restaurante.tech.domain.model.Pagamento;
import com.restaurante.tech.domain.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoRequestDTO dto, Pedido pedido) {
        Pagamento pagamento = new Pagamento();
        pagamento.setPedido(pedido);
        pagamento.setFormaPagamento(Pagamento.FormaPagamento.valueOf(dto.getFormaPagamento()));
        return pagamento;
    }

    public PagamentoResponseDTO toResponse(Pagamento entity) {
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.setId(entity.getId());
        dto.setPedidoId(entity.getPedido().getId());
        dto.setFormaPagamento(entity.getFormaPagamento().name());
        dto.setStatus(entity.getStatus().name());
        dto.setValor(entity.getValor());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setCodigoTransacao(entity.getCodigoTransacao());
        return dto;
    }
}