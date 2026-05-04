package com.restaurante.tech.domain.port;

import com.restaurante.tech.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    void deleteById(Long id);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}