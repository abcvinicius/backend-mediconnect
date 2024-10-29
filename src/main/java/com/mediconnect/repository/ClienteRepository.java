package com.mediconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mediconnect.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}