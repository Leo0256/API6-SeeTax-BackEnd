package com.SeeTax.entity.ValoresServicos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "valores_servicos")
public class ValoresServicos {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "grupo")
    private String grupo;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "pessoa")
    private Character pessoa;

    @JsonProperty("NomeServico")
    @Transient
    private String nome_servico;

    @Column(name = "max")
    @JsonProperty("ValorMaximo")
    private float max;

    @Column(name = "min")
    @JsonProperty("ValorMinimo")
    private float min;

    @Column(name = "media")
    @JsonProperty("ValorMedio")
    private float media;

    @Column(name = "periodicidade_max")
    @JsonProperty("PeriodicidadeValorMaximo")
    private String periodicidade_max;

    @Column(name = "periodicidade_min")
    @JsonProperty("PeriodicidadeValorMinimo")
    private String periodicidade_min;

    public ValoresServicos(int id, String grupo, String codigo, Character pessoa, String nome_servico, float max,
            float min, float media, String periodicidade_max, String periodicidade_min) {
        this.id = id;
        this.grupo = grupo;
        this.codigo = codigo;
        this.pessoa = pessoa;
        this.nome_servico = nome_servico;
        this.max = max;
        this.min = min;
        this.media = media;
        this.periodicidade_max = periodicidade_max;
        this.periodicidade_min = periodicidade_min;
    }

    public ValoresServicos() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Character getPessoa() {
        return pessoa;
    }

    public void setPessoa(Character pessoa) {
        this.pessoa = pessoa;
    }

    public String getNome_servico() {
        return nome_servico;
    }

    public void setNome_servico(String nome_servico) {
        this.nome_servico = nome_servico;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public String getPeriodicidade_max() {
        return periodicidade_max;
    }

    public void setPeriodicidade_max(String periodicidade_max) {
        this.periodicidade_max = periodicidade_max;
    }

    public String getPeriodicidade_min() {
        return periodicidade_min;
    }

    public void setPeriodicidade_min(String periodicidade_min) {
        this.periodicidade_min = periodicidade_min;
    }

}
