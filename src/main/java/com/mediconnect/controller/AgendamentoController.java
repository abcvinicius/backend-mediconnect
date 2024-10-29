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

import com.mediconnect.controller.request.AgendamentoRequest;
import com.mediconnect.controller.response.AgendamentoResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Medico;
import com.mediconnect.repository.AgendamentoRepository;
import com.mediconnect.repository.MedicoRepository;
import com.mediconnect.service.AgendamentoService;
import com.mediconnect.utils.AgendamentoBuilder;

@RequestMapping("/agendamentos")
@RestController
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private AgendamentoBuilder agendamentoBuilder;
        
    @PostMapping("/salvar")
    public ResponseEntity<AgendamentoResponse> salvarAgendamento(@RequestBody @Validated AgendamentoRequest agendamentoRequest, 
                                                                 UriComponentsBuilder uriBuilder) throws Exception {
        AgendamentoResponse agendamento = agendamentoService.salvarAgendamento(agendamentoRequest);
        URI uri = uriBuilder.path("/agendamentos/{id}").buildAndExpand(agendamento.getId()).toUri();
        return ResponseEntity.created(uri).body(agendamento);
    }

    @GetMapping("/todos")
    public List<AgendamentoResponse> listarTodosAgendamentos() {
        return agendamentoService.buscarTodosAgendamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> buscarAgendamentoPorId(@PathVariable Long id) {
        AgendamentoResponse agendamento = agendamentoService.buscarAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }
    
    // Buscar agendamentos pendentes por médico
    public List<AgendamentoResponse> buscarAgendamentosPendentesPorMedico(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        List<Agendamento> agendamentos = agendamentoRepository.findByMedicoAndStatus(medico, "Pendente");
        return agendamentoBuilder.respostaEmAgendamentoConversor(agendamentos);
    }
    
    @GetMapping("/pendentes/medico/{medicoId}")
    public List<AgendamentoResponse> listarAgendamentosPendentesPorMedico(@PathVariable Long medicoId) {
        return agendamentoService.buscarAgendamentosPendentesPorMedico(medicoId);
    }
}