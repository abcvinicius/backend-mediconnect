package com.mediconnect.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ConsultaResponse {

    private Long id;
    private LocalDateTime dataHora;
    private String descricao;
    private ClienteResponse cliente;
    private MedicoResponse medico;
    private AgendamentoResponse agendamento;
}