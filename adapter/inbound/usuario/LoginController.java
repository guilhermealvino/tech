package com.restaurante.tech.adapter.inbound.usuario;

import com.restaurante.tech.domain.usuario.Usuario;
import com.restaurante.tech.domain.usuario.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class LoginController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("senha");
        Usuario usuario = usuarioService.validarLogin(email, senha);

        return ResponseEntity.ok(Map.of("id", usuario.getId(), "tipo", usuario.getTipo()));
    }
}