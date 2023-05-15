package com.SeeTax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SeeTax.entity.GruposConsolidados.Grupos;
import com.SeeTax.entity.Instituicoes.Instituicao;
import com.SeeTax.entity.Servicos.Servicos;
import com.SeeTax.services.DataService;

@RestController
@CrossOrigin
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
    
    @Autowired
    private DataService service;

    @GetMapping(value = "/grupos")
    public List<Grupos> getGrupos() {
        return service.getGrupos();
    }

    @GetMapping(value = "/instituicoes")
    public List<Instituicao> getInstituicoes() {
        return service.getInstituicoes();
    }

    @GetMapping(value = "/servicos")
    public List<Servicos> getServicos() {
        return service.getServicos();
    }
}
