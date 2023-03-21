package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalAccounts {
    
    @JsonProperty("type")
    private String type;
    
    /* @JsonProperty("fees")
    private Fees fees; */
    
    @JsonProperty("serviceBundles")
    private List<ServiceBundles> serviceBundles;
    
    @JsonProperty("openingClosingChannels")
    private List<String> openingClosingChannels;
    
    @JsonProperty("additionalInfo")
    private String additionalInfo;
    
    @JsonProperty("transactionMethods")
    private List<String> transactionMethods;
    
    @JsonProperty("termsConditions")
    private TermsConditions termsConditions;
    
    @JsonProperty("incomeRate")
    private IncomeRate incomeRate;

    public PersonalAccounts(
            String type, /* Fees fees, */ List<ServiceBundles> serviceBundles,
            List<String> openingClosingChannels, String additionalInfo, List<String> transactionMethods,
            TermsConditions termsConditions, IncomeRate incomeRate) {
        this.type = type;
        /* this.fees = fees; */
        this.serviceBundles = serviceBundles;
        this.openingClosingChannels = openingClosingChannels;
        this.additionalInfo = additionalInfo;
        this.transactionMethods = transactionMethods;
        this.termsConditions = termsConditions;
        this.incomeRate = incomeRate;
    }

    public PersonalAccounts() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /* public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    } */

    public List<ServiceBundles> getServiceBundles() {
        return serviceBundles;
    }

    public void setServiceBundles(List<ServiceBundles> serviceBundles) {
        this.serviceBundles = serviceBundles;
    }

    public List<String> getOpeningClosingChannels() {
        return openingClosingChannels;
    }

    public void setOpeningClosingChannels(List<String> openingClosingChannels) {
        this.openingClosingChannels = openingClosingChannels;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<String> getTransactionMethods() {
        return transactionMethods;
    }

    public void setTransactionMethods(List<String> transactionMethods) {
        this.transactionMethods = transactionMethods;
    }

    public TermsConditions getTermsConditions() {
        return termsConditions;
    }

    public void setTermsConditions(TermsConditions termsConditions) {
        this.termsConditions = termsConditions;
    }

    public IncomeRate getIncomeRate() {
        return incomeRate;
    }

    public void setIncomeRate(IncomeRate incomeRate) {
        this.incomeRate = incomeRate;
    }

}
