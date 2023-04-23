package com.SeeTax.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;
import com.SeeTax.entity.TarifasValor.TarifasValor;
import com.SeeTax.entity.ValoresServicos.ValoresServicos;
import com.SeeTax.repository.TarifasInstituicaoRep;
import com.SeeTax.repository.TarifasValorRep;
import com.SeeTax.repository.ValoresServicosRep;

@Service
public class TarifasService {

    @Autowired
    private TarifasInstituicaoRep tarifasInstRep;

    @Autowired
    private TarifasValorRep tarifasValorRep;

    @Autowired
    private ValoresServicosRep vServicosRep;

    public List<TarifasInstituicao> getTarifasInstituicao() {
        return tarifasInstRep.findAll();
    }

    public List<TarifasValor> getTarifasValor() {
        return tarifasValorRep.findAll();
    }

    public List<ValoresServicos> getTarifasServicos() {
        return vServicosRep.findAll();
    }
    
}
