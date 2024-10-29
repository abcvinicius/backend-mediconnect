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

import com.mediconnect.controller.request.MedicoRequest;
import com.mediconnect.controller.response.MedicoResponse;
import com.mediconnect.service.MedicoService;

@RequestMapping("/medicos")
@RestController
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/salvar")
    public ResponseEntity<MedicoResponse> salvarMedico(@RequestBody @Validated MedicoRequest medicoRequest, 
                                                       UriComponentsBuilder uriBuilder) throws Exception {
        MedicoResponse medico = medicoService.salvarMedico(medicoRequest);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping("/todos")
    public List<MedicoResponse> listarTodosMedicos() {
        return medicoService.buscarTodosMedicos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscarMedicoPorId(@PathVariable Long id) {
        MedicoResponse medico = medicoService.buscarMedicoPorId(id);
        return ResponseEntity.ok(medico);
    }
}