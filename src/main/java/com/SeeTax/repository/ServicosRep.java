package com.SeeTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.Servicos.Servicos;

public interface ServicosRep extends JpaRepository<Servicos, Long> {
    
}
