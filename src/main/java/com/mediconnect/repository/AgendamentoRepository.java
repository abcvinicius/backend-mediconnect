package com.mediconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mediconnect.controller.response.AgendamentoResponse;
import com.mediconnect.model.Agendamento;
import com.mediconnect.model.Medico;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByMedicoAndStatus(Medico medico, String status);

	
}