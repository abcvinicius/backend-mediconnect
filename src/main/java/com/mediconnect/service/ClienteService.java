package com.mediconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediconnect.controller.request.ClienteRequest;
import com.mediconnect.controller.response.ClienteResponse;
import com.mediconnect.model.Cliente;
import com.mediconnect.repository.ClienteRepository;
import com.mediconnect.utils.ClienteBuilder;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteBuilder clienteBuilder;

    // Salvar Cliente
    public ClienteResponse salvarCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteBuilder.requesicaoEmClienteConversor(clienteRequest);
        return clienteBuilder.respostaEmClienteConversor(clienteRepository.save(cliente));
    }

    // Buscar todos os Clientes
    public List<ClienteResponse> buscarTodosClientes() {
        return clienteBuilder.respostaEmClienteConversor(clienteRepository.findAll());
    }

    // Buscar Cliente por ID
    public ClienteResponse buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return clienteBuilder.respostaEmClienteConversor(cliente);
    }
}
