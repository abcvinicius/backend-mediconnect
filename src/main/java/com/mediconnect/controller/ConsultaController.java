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

import com.mediconnect.controller.request.ConsultaRequest;
import com.mediconnect.controller.response.ConsultaResponse;
import com.mediconnect.service.ConsultaService;

@RequestMapping("/consultas")
@RestController
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/salvar")
    public ResponseEntity<ConsultaResponse> salvarConsulta(@RequestBody @Validated ConsultaRequest consultaRequest, 
                                                           UriComponentsBuilder uriBuilder) throws Exception {
        ConsultaResponse consulta = consultaService.salvarConsulta(consultaRequest);
        URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(consulta);
    }

    @GetMapping("/todas")
    public List<ConsultaResponse> listarTodasConsultas() {
        return consultaService.buscarTodasConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponse> buscarConsultaPorId(@PathVariable Long id) {
        ConsultaResponse consulta = consultaService.buscarConsultaPorId(id);
        return ResponseEntity.ok(consulta);
    }
    
    @PostMapping("/confirmar/{agendamentoId}")
    public ResponseEntity<ConsultaResponse> confirmarAgendamento(@PathVariable Long agendamentoId, 
                                                                 @RequestBody @Validated ConsultaRequest consultaRequest, 
                                                                 UriComponentsBuilder uriBuilder) throws Exception {
        ConsultaResponse consulta = consultaService.confirmarAgendamento(agendamentoId, consultaRequest);
        URI uri = uriBuilder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(consulta);
    }
}