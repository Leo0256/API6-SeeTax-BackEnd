package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Companies {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("cnpjNumber")
    private String cnpjNumber;
    
    @JsonProperty("urlComplementaryList")
    private String urlComplementaryList;
    
    @JsonProperty("personalAccounts")
    private List<PersonalAccountList> personalAccounts;

    public Companies(String name, String cnpjNumber, String urlComplementaryList,
            List<PersonalAccountList> personalAccounts) {
        this.name = name;
        this.cnpjNumber = cnpjNumber;
        this.urlComplementaryList = urlComplementaryList;
        this.personalAccounts = personalAccounts;
    }

    public Companies() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpjNumber() {
        return cnpjNumber;
    }

    public void setCnpjNumber(String cnpjNumber) {
        this.cnpjNumber = cnpjNumber;
    }

    public String getUrlComplementaryList() {
        return urlComplementaryList;
    }

    public void setUrlComplementaryList(String urlComplementaryList) {
        this.urlComplementaryList = urlComplementaryList;
    }

    public List<PersonalAccountList> getPersonalAccounts() {
        return personalAccounts;
    }

    public void setPersonalAccounts(List<PersonalAccountList> personalAccounts) {
        this.personalAccounts = personalAccounts;
    }
}
