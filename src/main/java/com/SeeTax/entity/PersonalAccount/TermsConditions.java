package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TermsConditions {
    
    @JsonProperty("minimumBalance")
    private MinMax minimumBalance;
    
    @JsonProperty("elegibilityCriteriaInfo")
    private String elegibilityCriteriaInfo;
    
    @JsonProperty("closingProcessInfo")
    private String closingProcessInfo;

    public TermsConditions(MinMax minimumBalance, String elegibilityCriteriaInfo, String closingProcessInfo) {
        this.minimumBalance = minimumBalance;
        this.elegibilityCriteriaInfo = elegibilityCriteriaInfo;
        this.closingProcessInfo = closingProcessInfo;
    }

    public TermsConditions() {}

    public MinMax getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(MinMax minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public String getElegibilityCriteriaInfo() {
        return elegibilityCriteriaInfo;
    }

    public void setElegibilityCriteriaInfo(String elegibilityCriteriaInfo) {
        this.elegibilityCriteriaInfo = elegibilityCriteriaInfo;
    }

    public String getClosingProcessInfo() {
        return closingProcessInfo;
    }

    public void setClosingProcessInfo(String closingProcessInfo) {
        this.closingProcessInfo = closingProcessInfo;
    }
}
