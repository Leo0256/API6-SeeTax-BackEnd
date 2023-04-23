package com.SeeTax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SeeTax.entity.Servicos.Servicos;

public interface ServicosRep extends JpaRepository<Servicos, Long> {
    
    @Query("SELECT a FROM Servicos a WHERE nome = ?1")
    public Optional<Servicos> findByNome(String nome);

}
