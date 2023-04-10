package com.SeeTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.GruposConsolidados.Grupos;

public interface GruposRep extends JpaRepository<Grupos, Long> {
    
}
