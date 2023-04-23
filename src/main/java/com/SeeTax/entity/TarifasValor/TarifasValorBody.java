package com.SeeTax.entity.TarifasValor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TarifasValorBody {
    
    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("value")
    private List<TarifasValor> tarifas;

    public TarifasValorBody(String context, List<TarifasValor> tarifas) {
        this.context = context;
        this.tarifas = tarifas;
    }

    public TarifasValorBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<TarifasValor> getTarifas() {
        return tarifas;
    }

    public void setTarifas(List<TarifasValor> tarifas) {
        this.tarifas = tarifas;
    }

}
