package com.SeeTax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;

public interface TarifasInstituicaoRep extends JpaRepository<TarifasInstituicao, Long> {
    
}
