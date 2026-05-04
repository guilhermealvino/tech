package com.restaurante.tech.adapter.outbound.repository;

import com.restaurante.tech.domain.model.Produto;
import com.restaurante.tech.domain.port.ProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoJpaRepository extends ProdutoRepository, JpaRepository<Produto, Long> {
    List<Produto> findByCategoria(String categoria);
    List<Produto> findByDisponivelTrue();
    boolean existsByNome(String nome);
}