package com.mediconnect.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mediconnect.controller.request.ConsultaRequest;
import com.mediconnect.controller.response.AgendamentoResponse;
import com.mediconnect.controller.response.ClienteResponse;
import com.mediconnect.controller.response.ConsultaResponse;
import com.mediconnect.controller.response.MedicoResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Cliente;
import com.mediconnect.model.Consulta;
import com.mediconnect.model.Medico;

@Component
public class ConsultaBuilder {

    // Organizar a lista de Resposta
    public List<ConsultaResponse> respostaEmConsultaConversor(List<Consulta> consultas) {
        return consultas.stream().map(this::respostaEmConsultaConversor).toList();
    }

    // Converter uma consulta em Resposta
    public ConsultaResponse respostaEmConsultaConversor(Consulta consulta) {
        return ConsultaResponse.builder()
                .id(consulta.getId())
                .dataHora(consulta.getDataHora())
                .descricao(consulta.getDescricao())
                .cliente(respostaEmClienteConversor(consulta.getCliente())) // Cliente na consulta
                .medico(respostaEmMedicoConversor(consulta.getMedico())) // Médico na consulta
                .agendamento(AgendamentoResponse.builder()
                        .id(consulta.getAgendamento().getId())
                        .dataHora(consulta.getAgendamento().getDataHora())
                        .status(consulta.getAgendamento().getStatus())
                        .build()) // Apenas o básico do agendamento
                .build();
    }

    // Converter uma requisição em Consulta
    public Consulta requesicaoEmConsultaConversor(ConsultaRequest consultaRequest, Agendamento agendamento) {
        return Consulta.builder()
                .descricao(consultaRequest.getDescricao())
                .agendamento(agendamento)
                .dataHora(agendamento.getDataHora())
                .cliente(agendamento.getCliente())
                .medico(agendamento.getMedico())
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

    private AgendamentoResponse respostaEmAgendamentoConversor(Agendamento agendamento) {
        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .dataHora(agendamento.getDataHora())
                .status(agendamento.getStatus())
                .cliente(respostaEmClienteConversor(agendamento.getCliente()))
                .medico(respostaEmMedicoConversor(agendamento.getMedico()))
                .build();
    }
}