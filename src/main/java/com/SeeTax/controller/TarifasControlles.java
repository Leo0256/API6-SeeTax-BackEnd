package com.SeeTax.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SeeTax.entity.Previsao;
import com.SeeTax.entity.Ranking;
import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;
import com.SeeTax.entity.TarifasValor.TarifasValor;
import com.SeeTax.entity.ValoresServicos.ValoresServicos;
import com.SeeTax.services.TarifasService;

@RestController
@CrossOrigin
@RequestMapping(
    value = "/tarifas",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = {MediaType.ALL_VALUE}
)
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

    
    @PostMapping(value = "/comparador")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public List<ValoresServicos> getComparadorTarifas(
        @RequestBody Map<String, List<String>> data
    ) {
        return service.getComparadorTarifas(data.get("grupos"), data.get("servicos"));
    }

    @GetMapping(value = "/ranking")
    public List<Ranking> getRanking(@RequestParam("servico") String servico) {
        return service.getRanking(servico);
    }

    @PostMapping(value = "/previsao")
    public List<Previsao> getPrevisao(@RequestBody Map<String, String> data) {
        try {
            return service.getPrevisao(data.get("cnpj"), data.get("servico"));
        }
        catch(Exception e) {
            return null;
        }
    }
}
