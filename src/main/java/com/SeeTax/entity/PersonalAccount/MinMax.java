package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MinMax {

    @JsonProperty("value")
    private String value;

    @JsonProperty("currency")
    private String currency;
    
    public MinMax(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public MinMax() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
