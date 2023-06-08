package com.SeeTax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.TarifasValor.TarifasValor;

public interface TarifasValorRep extends JpaRepository<TarifasValor, Long> {
    
    public List<TarifasValor> findByCnpjAndCodigo(String cnpj, String codigo);

}
