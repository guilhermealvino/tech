package com.restaurante.tech.domain.usuario;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, String nome, Usuario.TipoUsuario tipo) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
        usuario.setNome(nome);
        usuario.setTipo(tipo);
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario trocarSenha(Long id, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
        usuario.setSenha(novaSenha);
        usuario.setDataUltimaAlteracao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarPorNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario validarLogin(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
        if (!usuario.getSenha().equals(senha)) {
            throw new SenhaInvalidaException("Senha inválida");
        }
        return usuario;
    }

    public static class EmailJaCadastradoException extends RuntimeException {
        public EmailJaCadastradoException(String msg) { super(msg); }
    }
    public static class UsuarioNaoEncontradoException extends RuntimeException {
        public UsuarioNaoEncontradoException(String msg) { super(msg); }
    }
    public static class SenhaInvalidaException extends RuntimeException {
        public SenhaInvalidaException(String msg) { super(msg); }
    }
}