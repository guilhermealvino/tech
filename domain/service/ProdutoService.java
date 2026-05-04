package com.restaurante.tech.domain.service;

import com.restaurante.tech.domain.model.Produto;
import com.restaurante.tech.domain.port.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto criar(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Produto já cadastrado");
        }
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    public List<Produto> listarDisponiveis() {
        return produtoRepository.findByDisponivelTrue();
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        produtoExistente.setUrlImagem(produtoAtualizado.getUrlImagem());
        produtoExistente.setDisponivel(produtoAtualizado.getDisponivel());

        return produtoRepository.save(produtoExistente);
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    public void alterarDisponibilidade(Long id, boolean disponivel) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produto.setDisponivel(disponivel);
        produtoRepository.save(produto);
    }
}