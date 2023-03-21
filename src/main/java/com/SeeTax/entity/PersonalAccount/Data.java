package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    
    @JsonProperty("brand")
    private Brand brand;

    public Data(Brand brand) {
        this.brand = brand;
    }

    public Data() {}

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
