package com.mediconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mediconnect.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}