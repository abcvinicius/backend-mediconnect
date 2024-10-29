package com.mediconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediconnect.controller.request.MedicoRequest;
import com.mediconnect.controller.response.MedicoResponse;
import com.mediconnect.model.Medico;
import com.mediconnect.repository.MedicoRepository;
import com.mediconnect.utils.MedicoBuilder;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoBuilder medicoBuilder;

    // Salvar Medico
    public MedicoResponse salvarMedico(MedicoRequest medicoRequest) {
        Medico medico = medicoBuilder.requesicaoEmMedicoConversor(medicoRequest);
        return medicoBuilder.respostaEmMedicoConversor(medicoRepository.save(medico));
    }

    // Buscar todos os Medicos
    public List<MedicoResponse> buscarTodosMedicos() {
        return medicoBuilder.respostaEmMedicoConversor(medicoRepository.findAll());
    }

    // Buscar Medico por ID
    public MedicoResponse buscarMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medico não encontrado"));
        return medicoBuilder.respostaEmMedicoConversor(medico);
    }
}