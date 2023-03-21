package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prices {

    @JsonProperty("interval")
    private String interval;

    @JsonProperty("value")
    private String value;

    @JsonProperty("monthlyFee")
    private String monthlyFee;//

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("customers")
    private Customers customers;

    public Prices(String interval, String value, String monthlyFee, String currency, Customers customers) {
        this.interval = interval;
        this.value = value;
        this.monthlyFee = monthlyFee;
        this.currency = currency;
        this.customers = customers;
    }

    public Prices() {}

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(String monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
