package com.SeeTax.entity.GruposConsolidados;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupo_consolidado")
public class Grupos {
    
    @Id
    @Column(name = "codigo")
    @JsonProperty("Codigo")
    private String codigo;

    @Column(name = "nome")
    @JsonProperty("Nome")
    private String nome;

	public Grupos(String codigo, String nome) {
        setCodigo(codigo);
		setNome(nome);
	}

	public Grupos() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
