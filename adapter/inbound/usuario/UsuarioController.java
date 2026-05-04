package com.restaurante.tech.adapter.inbound.usuario;

import com.restaurante.tech.application.usuario.*;
import com.restaurante.tech.domain.usuario.Usuario;
import com.restaurante.tech.domain.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioCadastroDTO);
        Usuario usuarioSalvo = usuarioService.cadastrar(usuario);
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @RequestBody UsuarioAtualizacaoDTO dto) {
        Usuario atualizado = usuarioService.atualizar(id, dto.getNome(), dto.getTipo());
        return ResponseEntity.ok(UsuarioMapper.toDTO(atualizado));
    }

    @PutMapping("/{id}/senha")
    public ResponseEntity<UsuarioDTO> trocarSenha(@PathVariable Long id, @RequestBody UsuarioTrocaSenhaDTO dto) {
        Usuario atualizado = usuarioService.trocarSenha(id, dto.getNovaSenha());
        return ResponseEntity.ok(UsuarioMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscar(@RequestParam(required = false) String nome) {
        List<Usuario> usuarios;
        if (nome == null || nome.isBlank()) {
            usuarios = usuarioService.buscarTodos();
        } else {
            usuarios = usuarioService.buscarPorNome(nome);
        }
        List<UsuarioDTO> usuariosDto = usuarios.stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDto);
    }
}