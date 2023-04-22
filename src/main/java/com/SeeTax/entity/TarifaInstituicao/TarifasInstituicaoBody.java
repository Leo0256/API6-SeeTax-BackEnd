package com.SeeTax.entity.TarifaInstituicao;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TarifasInstituicaoBody {
    
    @JsonProperty("@odata.context")
    private String context;
    
    @JsonProperty("value")
    private List<TarifasInstituicao> tarifas;

    public TarifasInstituicaoBody(String context, List<TarifasInstituicao> tarifas) {
        this.context = context;
        this.tarifas = tarifas;
    }

    public TarifasInstituicaoBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<TarifasInstituicao> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<TarifasInstituicao> tarifas) {
        this.tarifas = tarifas;
    }

}
