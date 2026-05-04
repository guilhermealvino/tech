package com.restaurante.tech.adapter.outbound.usuario;

import com.restaurante.tech.domain.usuario.Usuario;
import com.restaurante.tech.domain.usuario.UsuarioRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositoryImpl extends JpaRepository<Usuario, Long>, UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    boolean existsByEmail(String email);
}