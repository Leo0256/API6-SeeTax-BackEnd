package com.SeeTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.TarifasValor.TarifasValor;

public interface TarifasValorRep extends JpaRepository<TarifasValor, Long> {
    
}
