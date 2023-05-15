package com.SeeTax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SeeTax.entity.Instituicoes.Instituicao;

public interface InstituicoesRep extends JpaRepository<Instituicao, Long> {
    
    @Query("SELECT a FROM Instituicao a WHERE cnpj = ?1")
    public Optional<Instituicao> findByCnpj(String cnpj);
    
}
