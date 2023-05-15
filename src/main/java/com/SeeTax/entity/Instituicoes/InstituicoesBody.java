package com.SeeTax.entity.Instituicoes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InstituicoesBody {
    
    @JsonProperty("@odata.context")
    private String context;

    @JsonProperty("value")
    private List<Instituicao> instituicoes;

    public InstituicoesBody(String context, List<Instituicao> instituicoes) {
        this.context = context;
        this.instituicoes = instituicoes;
    }

    public InstituicoesBody() {}

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

}
