package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceBundles {
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("services")
    private List<Services> services;
    
    @JsonProperty("prices")
    private List<Prices> prices;
    
    @JsonProperty("minimum")
    private MinMax minimum;
    
    @JsonProperty("maximum")
    private MinMax maximum;

    public ServiceBundles(
            String name, List<Services> services, List<Prices> prices,
            MinMax minimum, MinMax maximum
            ) {
        this.name = name;
        this.services = services;
        this.prices = prices;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public ServiceBundles() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
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
