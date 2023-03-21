package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Brand {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("companies")
    private List<Companies> companies;

    public Brand(String name, List<Companies> companies) {
        this.name = name;
        this.companies = companies;
    }

    public Brand() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Companies> companies) {
        this.companies = companies;
    }
}
