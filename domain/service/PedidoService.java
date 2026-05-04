package com.restaurante.tech.domain.service;

import com.restaurante.tech.domain.model.ItemPedido;
import com.restaurante.tech.domain.model.Pedido;
import com.restaurante.tech.domain.port.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido criar(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            item.setPedido(pedido);
            item.setPrecoUnitario(item.getProduto().getPreco());
            item.calcularSubtotal();
        }
        pedido.calcularTotal();
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> listarPorStatus(Pedido.StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(Long id, Pedido.StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    public Pedido adicionarItem(Long pedidoId, ItemPedido item) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        
        item.setPedido(pedido);
        item.setPrecoUnitario(item.getProduto().getPreco());
        item.calcularSubtotal();
        
        pedido.getItens().add(item);
        pedido.calcularTotal();
        
        return pedidoRepository.save(pedido);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }
}