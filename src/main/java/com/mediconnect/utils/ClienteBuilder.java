package com.mediconnect.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mediconnect.controller.request.ClienteRequest;
import com.mediconnect.controller.response.ClienteResponse;
import com.mediconnect.model.Cliente;

@Component
public class ClienteBuilder {

    // Organizar a lista de Resposta
    public List<ClienteResponse> respostaEmClienteConversor(List<Cliente> clientes) {
        return clientes.stream().map(this::respostaEmClienteConversor).toList();
    }

    // Converter um cliente em Resposta
    public ClienteResponse respostaEmClienteConversor(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }

    // Converter uma requisição em Cliente
    public Cliente requesicaoEmClienteConversor(ClienteRequest clienteRequest) {
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .cpf(clienteRequest.getCpf())
                .endereco(clienteRequest.getEndereco())
                .telefone(clienteRequest.getTelefone())
                .email(clienteRequest.getEmail())
                .build();
    }
}