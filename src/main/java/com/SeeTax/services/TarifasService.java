package com.SeeTax.services;

import java.util.ArrayList;
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

    public List<ValoresServicos> getComparadorTarifas(List<String> grupos, List<String> servicos) {
        List<ValoresServicos> valores = vServicosRep.findAll();
        List<ValoresServicos> filter = new ArrayList<>();

        // Possui grupos para filtrar
        if(grupos.size() > 0) {
            for (ValoresServicos valoresServico : valores) {
                // Filtra por grupo
                if(grupos.contains(valoresServico.getGrupo())) {
                    filter.add(valoresServico);
                }
            }
        }
        // Sem filtro por grupo
        else {
            filter.addAll(valores);
        }

        // Possui serviços para filtrar
        if(servicos.size() > 0) {
            return filter.stream().filter(a -> servicos.contains(a.getCodigo())).toList();
        }

        // Sem filtro por serviços
        return filter;
    }
    
}
