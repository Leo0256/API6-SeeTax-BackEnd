package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customers {
    
    @JsonProperty("rate")
    private String rate;

    public Customers(String rate) {
        this.rate = rate;
    }

    public Customers() {}

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
