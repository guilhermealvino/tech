package com.restaurante.tech.domain.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    boolean existsByEmail(String email);
}