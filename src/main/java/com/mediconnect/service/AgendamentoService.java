package com.mediconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediconnect.controller.request.AgendamentoRequest;
import com.mediconnect.controller.response.AgendamentoResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Cliente;
import com.mediconnect.model.Medico;
import com.mediconnect.repository.AgendamentoRepository;
import com.mediconnect.repository.ClienteRepository;
import com.mediconnect.repository.MedicoRepository;
import com.mediconnect.utils.AgendamentoBuilder;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendamentoBuilder agendamentoBuilder;

    // Salvar Agendamento
    public AgendamentoResponse salvarAgendamento(AgendamentoRequest agendamentoRequest) {
        Cliente cliente = clienteRepository.findById(agendamentoRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Medico medico = medicoRepository.findById(agendamentoRequest.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Medico não encontrado"));

        Agendamento agendamento = agendamentoBuilder.requesicaoEmAgendamentoConversor(agendamentoRequest, cliente, medico);
        return agendamentoBuilder.respostaEmAgendamentoConversor(agendamentoRepository.save(agendamento));
    }

    // Buscar todos os Agendamentos
    public List<AgendamentoResponse> buscarTodosAgendamentos() {
        return agendamentoBuilder.respostaEmAgendamentoConversor(agendamentoRepository.findAll());
    }

    // Buscar Agendamento por ID
    public AgendamentoResponse buscarAgendamentoPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return agendamentoBuilder.respostaEmAgendamentoConversor(agendamento);
    }
    
    // Buscar agendamentos pendentes por médico
    public List<AgendamentoResponse> buscarAgendamentosPendentesPorMedico(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        List<Agendamento> agendamentos = agendamentoRepository.findByMedicoAndStatus(medico, "Pendente");
        return agendamentoBuilder.respostaEmAgendamentoConversor(agendamentos);
    }
}