package com.SeeTax.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeeTax.entity.GruposConsolidados.Grupos;
import com.SeeTax.entity.Instituicoes.Instituicoes;
import com.SeeTax.entity.Servicos.Servicos;
import com.SeeTax.repository.GruposRep;
import com.SeeTax.repository.InstituicoesRep;
import com.SeeTax.repository.ServicosRep;

@Service
public class DataService {
    
    @Autowired
    private GruposRep gruposRep;

    @Autowired
    private InstituicoesRep instRep;

    @Autowired
    private ServicosRep servicosRep;

    public List<Grupos> getGrupos() {
        return gruposRep.findAll();
    }

    public List<Instituicoes> getInstituicoes() {
        return instRep.findAll();
    }

    public List<Servicos> getServicos() {
        return servicosRep.findAll();
    }
}
