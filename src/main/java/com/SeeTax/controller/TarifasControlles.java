package com.SeeTax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;
import com.SeeTax.entity.TarifasValor.TarifasValor;
import com.SeeTax.entity.ValoresServicos.ValoresServicos;
import com.SeeTax.services.TarifasService;

@RestController
@CrossOrigin
@RequestMapping(value = "/tarifas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarifasControlles {
    
    @Autowired
    private TarifasService service;

    @GetMapping(value = "/instituicoes")
    public List<TarifasInstituicao> getTarifasInstituicao() {
        return service.getTarifasInstituicao();
    }

    @GetMapping(value = "/valor")
    public List<TarifasValor> getTarifasValor() {
        return service.getTarifasValor();
    }

    @GetMapping(value = "/servicos")
    public List<ValoresServicos> getTarifasServicos() {
        return service.getTarifasServicos();
    }
}
