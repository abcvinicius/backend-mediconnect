package com.mediconnect.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mediconnect.controller.request.AgendamentoRequest;
import com.mediconnect.controller.response.AgendamentoResponse;
import com.mediconnect.controller.response.ClienteResponse;
import com.mediconnect.controller.response.MedicoResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Cliente;
import com.mediconnect.model.Medico;

@Component
public class AgendamentoBuilder {

    // Organizar a lista de Resposta
    public List<AgendamentoResponse> respostaEmAgendamentoConversor(List<Agendamento> agendamentos) {
        return agendamentos.stream().map(this::respostaEmAgendamentoConversor).toList();
    }

    // Converter um agendamento em Resposta
    public AgendamentoResponse respostaEmAgendamentoConversor(Agendamento agendamento) {
        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .dataHora(agendamento.getDataHora())
                .status(agendamento.getStatus())
                .cliente(respostaEmClienteConversor(agendamento.getCliente()))
                .medico(respostaEmMedicoConversor(agendamento.getMedico()))
                .build();
    }

    // Converter uma requisição em Agendamento
    public Agendamento requesicaoEmAgendamentoConversor(AgendamentoRequest agendamentoRequest, Cliente cliente, Medico medico) {
        return Agendamento.builder()
                .dataHora(agendamentoRequest.getDataHora())
                .cliente(cliente)
                .medico(medico)
                .status("Pendente")
                .build();
    }

    // Métodos de conversão auxiliar
    private ClienteResponse respostaEmClienteConversor(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }

    private MedicoResponse respostaEmMedicoConversor(Medico medico) {
        return MedicoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .cpf(medico.getCpf())
                .endereco(medico.getEndereco())
                .telefone(medico.getTelefone())
                .email(medico.getEmail())
                .build();
    }
}