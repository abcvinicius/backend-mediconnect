package com.mediconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediconnect.controller.request.ConsultaRequest;
import com.mediconnect.controller.response.ConsultaResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Consulta;
import com.mediconnect.repository.AgendamentoRepository;
import com.mediconnect.repository.ConsultaRepository;
import com.mediconnect.utils.ConsultaBuilder;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ConsultaBuilder consultaBuilder;

    // Salvar Consulta
    public ConsultaResponse salvarConsulta(ConsultaRequest consultaRequest) {
        Agendamento agendamento = agendamentoRepository.findById(consultaRequest.getAgendamentoId())
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Consulta consulta = consultaBuilder.requesicaoEmConsultaConversor(consultaRequest, agendamento);
        return consultaBuilder.respostaEmConsultaConversor(consultaRepository.save(consulta));
    }

    // Buscar todas as Consultas
    public List<ConsultaResponse> buscarTodasConsultas() {
        return consultaBuilder.respostaEmConsultaConversor(consultaRepository.findAll());
    }

    // Buscar Consulta por ID
    public ConsultaResponse buscarConsultaPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        return consultaBuilder.respostaEmConsultaConversor(consulta);
    }
    
    // Confirmar agendamento e criar consulta
    public ConsultaResponse confirmarAgendamento(Long agendamentoId, ConsultaRequest consultaRequest) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        
        if (!"Pendente".equals(agendamento.getStatus())) {
            throw new RuntimeException("Agendamento já confirmado ou cancelado");
        }

        // Atualiza o status do agendamento para "Confirmado"
        agendamento.setStatus("Confirmado");
        agendamentoRepository.save(agendamento);

        // Cria a consulta com base no agendamento confirmado
        Consulta consulta = consultaBuilder.requesicaoEmConsultaConversor(consultaRequest, agendamento);
        return consultaBuilder.respostaEmConsultaConversor(consultaRepository.save(consulta));
    }
}