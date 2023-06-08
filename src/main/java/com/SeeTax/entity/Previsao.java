package com.SeeTax.entity;

public class Previsao {
    
    private String data;

    private String valor_max;

    public Previsao(String data, String valor_max) {
        this.data = data;
        this.valor_max = valor_max;
    }

    public Previsao() {}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor_max() {
        return valor_max;
    }

    public void setValor_max(String valor_max) {
        this.valor_max = valor_max;
    }

}
