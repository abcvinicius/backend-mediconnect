package com.mediconnect.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mediconnect.controller.request.MedicoRequest;
import com.mediconnect.controller.response.MedicoResponse;
import com.mediconnect.model.Medico;

@Component
public class MedicoBuilder {

    // Organizar a lista de Resposta
    public List<MedicoResponse> respostaEmMedicoConversor(List<Medico> medicos) {
        return medicos.stream().map(this::respostaEmMedicoConversor).toList();
    }

    // Converter um medico em Resposta
    public MedicoResponse respostaEmMedicoConversor(Medico medico) {
        return MedicoResponse.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .cpf(medico.getCpf())
                .endereco(medico.getEndereco())
                .telefone(medico.getTelefone())
                .email(medico.getEmail())
                .build();
    }

    // Converter uma requisição em Medico
    public Medico requesicaoEmMedicoConversor(MedicoRequest medicoRequest) {
        return Medico.builder()
                .nome(medicoRequest.getNome())
                .cpf(medicoRequest.getCpf())
                .endereco(medicoRequest.getEndereco())
                .telefone(medicoRequest.getTelefone())
                .email(medicoRequest.getEmail())
                .build();
    }
}
