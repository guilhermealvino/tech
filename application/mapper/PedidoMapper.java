package com.restaurante.tech.application.mapper;

import com.restaurante.tech.application.dto.*;
import com.restaurante.tech.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    public Pedido toEntity(PedidoRequestDTO dto, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setObservacoes(dto.getObservacoes());
        return pedido;
    }

    public PedidoResponseDTO toResponse(Pedido entity) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getCliente().getId());
        dto.setClienteNome(entity.getCliente().getNome());
        dto.setStatus(entity.getStatus().name());
        dto.setValorTotal(entity.getValorTotal());
        dto.setDataPedido(entity.getDataPedido());
        dto.setObservacoes(entity.getObservacoes());
        dto.setItens(toItemResponseList(entity.getItens()));
        return dto;
    }

    public List<PedidoResponseDTO> toResponseList(List<Pedido> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ItemPedido toItemEntity(ItemPedidoRequestDTO dto, Produto produto) {
        ItemPedido item = new ItemPedido();
        item.setProduto(produto);
        item.setQuantidade(dto.getQuantidade());
        item.setPrecoUnitario(produto.getPreco());
        item.calcularSubtotal();
        return item;
    }

    private ItemPedidoResponseDTO toItemResponse(ItemPedido entity) {
        ItemPedidoResponseDTO dto = new ItemPedidoResponseDTO();
        dto.setId(entity.getId());
        dto.setProdutoId(entity.getProduto().getId());
        dto.setProdutoNome(entity.getProduto().getNome());
        dto.setQuantidade(entity.getQuantidade());
        dto.setPrecoUnitario(entity.getPrecoUnitario());
        dto.setSubtotal(entity.getSubtotal());
        return dto;
    }

    private List<ItemPedidoResponseDTO> toItemResponseList(List<ItemPedido> itens) {
        return itens.stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());
    }
}