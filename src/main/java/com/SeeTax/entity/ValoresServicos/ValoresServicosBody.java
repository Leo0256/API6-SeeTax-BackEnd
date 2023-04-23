package com.SeeTax.entity.ValoresServicos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValoresServicosBody {
    
    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("value")
    private List<ValoresServicos> servicos;

    public ValoresServicosBody(String context, List<ValoresServicos> servicos) {
        this.context = context;
        this.servicos = servicos;
    }

    public ValoresServicosBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<ValoresServicos> getServicos() {
        return servicos;
    }

    public void setServicos(List<ValoresServicos> servicos) {
        this.servicos = servicos;
    }

}
