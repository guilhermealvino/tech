package com.restaurante.tech.application.service;

import com.restaurante.tech.application.dto.*;
import com.restaurante.tech.application.mapper.ClienteMapper;
import com.restaurante.tech.domain.model.Cliente;
import com.restaurante.tech.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteApplicationService {

    private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente clienteSalvo = clienteService.criar(cliente);
        return clienteMapper.toResponse(clienteSalvo);
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        return clienteService.buscarPorId(id)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteMapper.toResponseList(clienteService.listarTodos());
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente clienteAtualizado = clienteMapper.toEntity(dto);
        Cliente clienteSalvo = clienteService.atualizar(id, clienteAtualizado);
        return clienteMapper.toResponse(clienteSalvo);
    }

    public void excluir(Long id) {
        clienteService.excluir(id);
    }
}