package com.restaurante.tech.application.service;

import com.restaurante.tech.application.dto.*;
import com.restaurante.tech.application.mapper.PedidoMapper;
import com.restaurante.tech.domain.model.Cliente;
import com.restaurante.tech.domain.model.ItemPedido;
import com.restaurante.tech.domain.model.Pedido;
import com.restaurante.tech.domain.model.Produto;
import com.restaurante.tech.domain.service.ClienteService;
import com.restaurante.tech.domain.service.PedidoService;
import com.restaurante.tech.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoApplicationService {

    private final PedidoService pedidoService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final PedidoMapper pedidoMapper;

    @Transactional
    public PedidoResponseDTO criar(PedidoRequestDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Pedido pedido = pedidoMapper.toEntity(dto, cliente);

        if (dto.getItens() != null && !dto.getItens().isEmpty()) {
            for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
                Produto produto = produtoService.buscarPorId(itemDto.getProdutoId())
                        .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
                
                if (!produto.getDisponivel()) {
                    throw new IllegalArgumentException("Produto não disponível: " + produto.getNome());
                }
                
                ItemPedido item = pedidoMapper.toItemEntity(itemDto, produto);
                pedido.getItens().add(item);
            }
        }

        Pedido pedidoSalvo = pedidoService.criar(pedido);
        return pedidoMapper.toResponse(pedidoSalvo);
    }

    public PedidoResponseDTO buscarPorId(Long id) {
        return pedidoService.buscarPorId(id)
                .map(pedidoMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }

    public List<PedidoResponseDTO> listarTodos() {
        return pedidoMapper.toResponseList(pedidoService.listarTodos());
    }

    public List<PedidoResponseDTO> listarPorCliente(Long clienteId) {
        return pedidoMapper.toResponseList(pedidoService.listarPorCliente(clienteId));
    }

    public List<PedidoResponseDTO> listarPorStatus(String status) {
        return pedidoMapper.toResponseList(
                pedidoService.listarPorStatus(Pedido.StatusPedido.valueOf(status)));
    }

    public PedidoResponseDTO atualizarStatus(Long id, String status) {
        Pedido pedidoSalvo = pedidoService.atualizarStatus(id, Pedido.StatusPedido.valueOf(status));
        return pedidoMapper.toResponse(pedidoSalvo);
    }

    public void excluir(Long id) {
        pedidoService.excluir(id);
    }
}