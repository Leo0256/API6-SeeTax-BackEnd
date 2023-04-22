package com.SeeTax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SeeTax.entity.Instituicoes.Instituicoes;

public interface InstituicoesRep extends JpaRepository<Instituicoes, Long> {
    
    @Query("SELECT a FROM Instituicoes a WHERE cnpj = ?1")
    public Optional<Instituicoes> findByCnpj(String cnpj);
    
}
