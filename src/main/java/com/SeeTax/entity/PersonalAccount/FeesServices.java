package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeesServices {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("chargingTriggerInfo")
    private String chargingTriggerInfo;
    
    @JsonProperty("prices")
    private List<Prices> prices;
    
    @JsonProperty("minimum")
    private MinMax minimum;
    
    @JsonProperty("maximum")
    private MinMax maximum;
    
    public FeesServices(
            String name, String code, String chargingTriggerInfo,
            List<Prices> prices, MinMax minimum, MinMax maximum
        ) {
        this.name = name;
        this.code = code;
        this.chargingTriggerInfo = chargingTriggerInfo;
        this.prices = prices;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public FeesServices() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChargingTriggerInfo() {
        return chargingTriggerInfo;
    }

    public void setChargingTriggerInfo(String chargingTriggerInfo) {
        this.chargingTriggerInfo = chargingTriggerInfo;
    }

    public List<Prices> getPrices() {
        return prices;
    }

    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }

    public MinMax getMinimum() {
        return minimum;
    }

    public void setMinimum(MinMax minimum) {
        this.minimum = minimum;
    }

    public MinMax getMaximum() {
        return maximum;
    }

    public void setMaximum(MinMax maximum) {
        this.maximum = maximum;
    }

}
