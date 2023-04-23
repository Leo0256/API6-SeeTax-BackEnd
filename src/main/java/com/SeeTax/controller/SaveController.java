package com.SeeTax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SeeTax.services.SaveService;

@RestController
@CrossOrigin
@RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
public class SaveController {
    
    @Autowired
    private SaveService service;

    @GetMapping(value = "/servicos")
    public void saveServicos() {
        try {
            service.saveServicos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping(value = "/grupos")
    public void saveGrupos() {
        try {
            service.saveGrupos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping(value = "/instituicoes")
    public void saveInstituicoes() {
        try {
            service.saveInstituicoes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping(value = "/tarifas/instituicoes")
    public void saveTarifasInstituicoes() {
        try {
            service.saveTarifasInstituicoes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping(value = "/tarifas/valor")
    public void saveTarifasValor() {
        try {
            service.saveTarifasValor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
