package com.SeeTax.entity.TarifaInstituicao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tarifas_instituicao")
public class TarifasInstituicao {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo")
    @JsonProperty("CodigoServico")
    private String codigo;

    @JsonProperty("Servico")
    @Transient
    private String servico;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "pessoa")
    private Character pessoa;

    @Column(name = "unidade")
    @JsonProperty("Unidade")
    private String unidade;

    @Column(name = "data_vigencia")
    @JsonProperty("DataVigencia")
    private Date data_vigencia;

    @Column(name = "valor_max")
    @JsonProperty("ValorMaximo")
    private float valor_max;

    @Column(name = "valor_tipo")
    @JsonProperty("TipoValor")
    private String valor_tipo;

    @Column(name = "periodicidade")
    @JsonProperty("Periodicidade")
    private String periodicidade;

    public TarifasInstituicao(int id, String codigo, String servico, String cnpj, Character pessoa, String unidade,
            Date data_vigencia, float valor_max, String valor_tipo, String periodicidade) {
        this.id = id;
        this.codigo = codigo;
        this.servico = servico;
        this.cnpj = cnpj;
        this.pessoa = pessoa;
        this.unidade = unidade;
        this.data_vigencia = data_vigencia;
        this.valor_max = valor_max;
        this.valor_tipo = valor_tipo;
        this.periodicidade = periodicidade;
    }

    public TarifasInstituicao() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Character getPessoa() {
        return pessoa;
    }

    public void setPessoa(Character pessoa) {
        this.pessoa = pessoa;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Date getData_vigencia() {
        return data_vigencia;
    }

    public void setData_vigencia(Date data_vigencia) {
        this.data_vigencia = data_vigencia;
    }

    public float getValor_max() {
        return valor_max;
    }

    public void setValor_max(float valor_max) {
        this.valor_max = valor_max;
    }

    public String getValor_tipo() {
        return valor_tipo;
    }

    public void setValor_tipo(String valor_tipo) {
        this.valor_tipo = valor_tipo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

}
