package com.restaurante.tech.adapter.inbound.controller;

import com.restaurante.tech.application.dto.PagamentoRequestDTO;
import com.restaurante.tech.application.dto.PagamentoResponseDTO;
import com.restaurante.tech.application.service.PagamentoApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
@Tag(name = "Pagamentos", description = "API de gerenciamento de pagamentos")
public class PagamentoController {

    private final PagamentoApplicationService pagamentoService;

    @PostMapping
    @Operation(summary = "Processar pagamento")
    public ResponseEntity<PagamentoResponseDTO> processar(@Valid @RequestBody PagamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.processar(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pagamento por ID")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping("/pedido/{pedidoId}")
    @Operation(summary = "Buscar pagamento por ID do pedido")
    public ResponseEntity<PagamentoResponseDTO> buscarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pagamentoService.buscarPorPedido(pedidoId));
    }

    @PostMapping("/{id}/estornar")
    @Operation(summary = "Estornar pagamento")
    public ResponseEntity<PagamentoResponseDTO> estornar(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.estornar(id));
    }
}