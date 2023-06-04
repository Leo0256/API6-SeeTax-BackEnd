package com.SeeTax.entity;

import java.util.List;

import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;

public class Ranking {
    
    private Integer position;

    private TarifasInstituicao instituicao;

    public Ranking(Integer position, TarifasInstituicao instituicao) {
        this.position = position;
        this.instituicao = instituicao;
    }

    public Ranking() {}

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public TarifasInstituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(TarifasInstituicao instituicao) {
        this.instituicao = instituicao;
    }

}
