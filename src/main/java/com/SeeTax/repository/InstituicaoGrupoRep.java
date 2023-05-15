package com.SeeTax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SeeTax.entity.InstituicaoGrupo;
import com.SeeTax.entity.GruposConsolidados.Grupos;
import com.SeeTax.entity.Instituicoes.Instituicao;

public interface InstituicaoGrupoRep extends JpaRepository<InstituicaoGrupo, Long> {

    @Query("SELECT a FROM InstituicaoGrupo a WHERE grupo = ?1 AND instituicao = ?2")
    public Optional<InstituicaoGrupo> find(Grupos grupo, Instituicao inst);
    
}
