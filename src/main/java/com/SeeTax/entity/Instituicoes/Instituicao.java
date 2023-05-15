package com.SeeTax.entity.Instituicoes;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instituicao")
public class Instituicao {
    
    @Id
    @Column(name = "cnpj", unique = true)
    @JsonProperty("Cnpj")
    private String cnpj;

    @Column(name = "nome")
    @JsonProperty("Nome")
    private String nome;

    public Instituicao(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Instituicao() {}

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
