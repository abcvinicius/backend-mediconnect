package com.mediconnect.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicoRequest {

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
}