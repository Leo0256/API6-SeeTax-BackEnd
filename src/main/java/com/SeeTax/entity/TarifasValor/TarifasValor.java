package com.SeeTax.entity.TarifasValor;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tarifas_valor")
public class TarifasValor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cnpj")
    @JsonProperty("Cnpj")
    private String cnpj;

    @Column(name = "codigo")
    private String codigo;

    @JsonProperty("RazaoSocial")
    @Transient
    private String razao_social;

    @Column(name = "valor_max")
    @JsonProperty("ValorMaximo")
    private float valor_max;

    @Column(name = "periodicidade")
    @JsonProperty("Periodicidade")
    private String periodicidade;

    @Column(name = "pessoa")
    private Character pessoa;

    @Column(name = "data")
    private String data;

    public TarifasValor(int id, String cnpj, String codigo, float valor_max, String periodicidade,
        Character pessoa, String data) {
        this.id = id;
        this.cnpj = cnpj;
        this.codigo = codigo;
        this.valor_max = valor_max;
        this.periodicidade = periodicidade;
        this.pessoa = pessoa;
        this.data = data;
    }

    public TarifasValor(int id, String cnpj, String codigo, String razao_social, float valor_max,
            String periodicidade) {
        this.id = id;
        this.cnpj = cnpj;
        this.codigo = codigo;
        this.razao_social = razao_social;
        this.valor_max = valor_max;
        this.periodicidade = periodicidade;
    }

    public TarifasValor() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public float getValor_max() {
        return valor_max;
    }

    public void setValor_max(float valor_max) {
        this.valor_max = valor_max;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Character getPessoa() {
        return pessoa;
    }

    public void setPessoa(Character pessoa) {
        this.pessoa = pessoa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
