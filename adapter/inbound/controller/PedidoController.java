package com.restaurante.tech.adapter.inbound.controller;

import com.restaurante.tech.application.dto.PedidoRequestDTO;
import com.restaurante.tech.application.dto.PedidoResponseDTO;
import com.restaurante.tech.application.service.PedidoApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
public class PedidoController {

    private final PedidoApplicationService pedidoService;

    @PostMapping
    @Operation(summary = "Criar novo pedido")
    public ResponseEntity<PedidoResponseDTO> criar(@Valid @RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criar(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar pedidos por cliente")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(clienteId));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Listar pedidos por status")
    public ResponseEntity<List<PedidoResponseDTO>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(pedidoService.listarPorStatus(status));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(@PathVariable Long id, 
                                                              @RequestParam String status) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir pedido")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}