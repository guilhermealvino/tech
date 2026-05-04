package com.restaurante.tech.domain.usuario;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    public enum TipoUsuario {
        DONO_RESTAURANTE, CLIENTE
    }
}