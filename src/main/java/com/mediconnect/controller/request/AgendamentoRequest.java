package com.mediconnect.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AgendamentoRequest {

    private LocalDateTime dataHora;
    private Long clienteId;
    private Long medicoId;
}