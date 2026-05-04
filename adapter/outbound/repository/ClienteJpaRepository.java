package com.restaurante.tech.adapter.outbound.repository;

import com.restaurante.tech.domain.model.Cliente;
import com.restaurante.tech.domain.port.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends ClienteRepository, JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}