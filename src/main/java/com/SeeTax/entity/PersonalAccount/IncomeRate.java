package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomeRate {
    
    @JsonProperty("savingAccount")
    private String savingAccount;
    
    @JsonProperty("prepaidPaymentAccount")
    private String prepaidPaymentAccount;

    public IncomeRate(String savingAccount, String prepaidPaymentAccount) {
        this.savingAccount = savingAccount;
        this.prepaidPaymentAccount = prepaidPaymentAccount;
    }

    public IncomeRate() {}

    public String getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(String savingAccount) {
        this.savingAccount = savingAccount;
    }

    public String getPrepaidPaymentAccount() {
        return prepaidPaymentAccount;
    }

    public void setPrepaidPaymentAccount(String prepaidPaymentAccount) {
        this.prepaidPaymentAccount = prepaidPaymentAccount;
    }
}
