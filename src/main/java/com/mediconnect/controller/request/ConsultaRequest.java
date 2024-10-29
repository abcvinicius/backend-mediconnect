package com.mediconnect.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaRequest {

    private String descricao;
    private Long agendamentoId;
}