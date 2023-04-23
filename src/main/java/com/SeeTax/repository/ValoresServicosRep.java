package com.SeeTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.ValoresServicos.ValoresServicos;

public interface ValoresServicosRep extends JpaRepository<ValoresServicos, Long> {
    
}
