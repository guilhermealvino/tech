package com.restaurante.tech.domain.port;

import com.restaurante.tech.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Produto save(Produto produto);
    Optional<Produto> findById(Long id);
    List<Produto> findAll();
    List<Produto> findByCategoria(String categoria);
    List<Produto> findByDisponivelTrue();
    void deleteById(Long id);
    boolean existsByNome(String nome);
}