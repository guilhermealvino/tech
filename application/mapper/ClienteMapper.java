package com.restaurante.tech.application.mapper;

import com.restaurante.tech.application.dto.ClienteRequestDTO;
import com.restaurante.tech.application.dto.ClienteResponseDTO;
import com.restaurante.tech.domain.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCpf(dto.getCpf());
        return cliente;
    }

    public ClienteResponseDTO toResponse(Cliente entity) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setEndereco(entity.getEndereco());
        dto.setCpf(entity.getCpf());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setAtivo(entity.getAtivo());
        return dto;
    }

    public List<ClienteResponseDTO> toResponseList(List<Cliente> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void updateEntity(ClienteRequestDTO dto, Cliente cliente) {
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
    }
}