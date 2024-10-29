package com.mediconnect.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mediconnect.controller.request.ClienteRequest;
import com.mediconnect.controller.response.ClienteResponse;
import com.mediconnect.service.ClienteService;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<ClienteResponse> salvarCliente(@RequestBody @Validated ClienteRequest clienteRequest, 
                                                         UriComponentsBuilder uriBuilder) throws Exception {
        ClienteResponse cliente = clienteService.salvarCliente(clienteRequest);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping("/todos")
    public List<ClienteResponse> listarTodosClientes() {
        return clienteService.buscarTodosClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable Long id) {
        ClienteResponse cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }
}