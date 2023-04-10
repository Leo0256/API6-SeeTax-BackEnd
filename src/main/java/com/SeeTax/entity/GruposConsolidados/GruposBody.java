package com.SeeTax.entity.GruposConsolidados;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GruposBody {

    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("value")
    private List<Grupos> grupos;

    public GruposBody(String context, List<Grupos> grupos) {
        this.context = context;
        this.grupos = grupos;
    }

    public GruposBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Grupos> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupos> grupos) {
        this.grupos = grupos;
    }
    
}
