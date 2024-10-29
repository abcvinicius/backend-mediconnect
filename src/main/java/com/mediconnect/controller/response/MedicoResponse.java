package com.mediconnect.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoResponse {

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
}