package com.SeeTax.entity.Servicos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServicosBody {
    
    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("value")
    private List<Servicos> servicos;

    public ServicosBody(String context, List<Servicos> servicos) {
        this.context = context;
        this.servicos = servicos;
    }

    public ServicosBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Servicos> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servicos> servicos) {
        this.servicos = servicos;
    }

}
