package com.restaurante.tech.adapter.inbound.controller;

import com.restaurante.tech.application.dto.TokenRequestDTO;
import com.restaurante.tech.application.dto.TokenResponseDTO;
import com.restaurante.tech.application.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @Value("${auth.password:admin}")
    private String configuredPassword;

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> token(@Valid @RequestBody TokenRequestDTO req) {

        if (!configuredPassword.equals(req.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String token = tokenService.generateToken(req.getUsername(), req.getScope());
        TokenResponseDTO resp = new TokenResponseDTO(token, "Bearer", tokenService.getExpiresInSeconds());
        return ResponseEntity.ok(resp);
    }
}
