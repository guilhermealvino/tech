package com.restaurante.tech.application.service;

import com.restaurante.tech.application.dto.*;
import com.restaurante.tech.application.mapper.ProdutoMapper;
import com.restaurante.tech.domain.model.Produto;
import com.restaurante.tech.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoApplicationService {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = produtoMapper.toEntity(dto);
        Produto produtoSalvo = produtoService.criar(produto);
        return produtoMapper.toResponse(produtoSalvo);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        return produtoService.buscarPorId(id)
                .map(produtoMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoMapper.toResponseList(produtoService.listarTodos());
    }

    public List<ProdutoResponseDTO> listarPorCategoria(String categoria) {
        return produtoMapper.toResponseList(produtoService.listarPorCategoria(categoria));
    }

    public List<ProdutoResponseDTO> listarDisponiveis() {
        return produtoMapper.toResponseList(produtoService.listarDisponiveis());
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produtoAtualizado = produtoMapper.toEntity(dto);
        Produto produtoSalvo = produtoService.atualizar(id, produtoAtualizado);
        return produtoMapper.toResponse(produtoSalvo);
    }

    public void excluir(Long id) {
        produtoService.excluir(id);
    }

    public void alterarDisponibilidade(Long id, boolean disponivel) {
        produtoService.alterarDisponibilidade(id, disponivel);
    }
}