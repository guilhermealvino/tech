package com.restaurante.tech.application.mapper;

import com.restaurante.tech.application.dto.ProdutoRequestDTO;
import com.restaurante.tech.application.dto.ProdutoResponseDTO;
import com.restaurante.tech.domain.model.Produto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setUrlImagem(dto.getUrlImagem());
        produto.setDisponivel(dto.getDisponivel());
        return produto;
    }

    public ProdutoResponseDTO toResponse(Produto entity) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setPreco(entity.getPreco());
        dto.setCategoria(entity.getCategoria());
        dto.setUrlImagem(entity.getUrlImagem());
        dto.setDisponivel(entity.getDisponivel());
        dto.setDataCadastro(entity.getDataCadastro());
        return dto;
    }

    public List<ProdutoResponseDTO> toResponseList(List<Produto> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}