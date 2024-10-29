package com.mediconnect.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AgendamentoResponse {

    private Long id;
    private LocalDateTime dataHora;
    private String status;
    private ClienteResponse cliente;
    private MedicoResponse medico;
}