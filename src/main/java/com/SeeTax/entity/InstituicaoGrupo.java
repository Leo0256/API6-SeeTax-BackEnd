package com.SeeTax.entity;

import com.SeeTax.entity.GruposConsolidados.Grupos;
import com.SeeTax.entity.Instituicoes.Instituicoes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instituicao_grupo")
public class InstituicaoGrupo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "grupo", referencedColumnName = "codigo")
    private Grupos grupo;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "inst")
    private Instituicoes instituicao;

    public InstituicaoGrupo(int id, Grupos grupo, Instituicoes instituicao) {
        this.id = id;
        this.grupo = grupo;
        this.instituicao = instituicao;
    }

    public InstituicaoGrupo() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }

    public Instituicoes getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicoes instituicao) {
        this.instituicao = instituicao;
    }

}
