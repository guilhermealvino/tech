package com.restaurante.tech.adapter.inbound.controller;

import com.restaurante.tech.application.dto.ProdutoRequestDTO;
import com.restaurante.tech.application.dto.ProdutoResponseDTO;
import com.restaurante.tech.application.service.ProdutoApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@Tag(name = "Produtos", description = "API de gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoApplicationService produtoService;

    @PostMapping
    @Operation(summary = "Criar novo produto")
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criar(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar produtos por categoria")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(categoria));
    }

    @GetMapping("/disponiveis")
    @Operation(summary = "Listar produtos disponíveis")
    public ResponseEntity<List<ProdutoResponseDTO>> listarDisponiveis() {
        return ResponseEntity.ok(produtoService.listarDisponiveis());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, 
                                                         @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidade")
    @Operation(summary = "Alterar disponibilidade do produto")
    public ResponseEntity<Void> alterarDisponibilidade(@PathVariable Long id, 
                                                         @RequestParam boolean disponivel) {
        produtoService.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.ok().build();
    }
}