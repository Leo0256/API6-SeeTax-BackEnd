package com.SeeTax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping(value = "/tarifas/servicos")
    public void saveTarifasServicos() {
        try {
            service.saveTarifasServicos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @PostMapping(value = "/tarifas/inst")
    @ResponseBody
    public ResponseEntity<String> saveTarifasInstituicao(@RequestParam("file") MultipartFile file) {
        System.out.println("alsoioioif");
        try {
            //service.saveTarifasInstituicao();
            System.out.println(String.valueOf(file != null).toString());
            return new ResponseEntity<>(String.valueOf(file != null).toString(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>("zebra", HttpStatus.BAD_REQUEST);
        }
    }

}
