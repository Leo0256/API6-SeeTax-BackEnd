package com.SeeTax.entity.PersonalAccount;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Fees {
    
    @JsonProperty("priorityServices")
    private List<FeesServices> priorityServices;
    
    @JsonProperty("otherServices")
    private List<FeesServices> otherServices;

    public Fees(List<FeesServices> priorityServices, List<FeesServices> otherServices) {
        this.priorityServices = priorityServices;
        this.otherServices = otherServices;
    }

    public Fees() {}

    public List<FeesServices> getPriorityServices() {
        return priorityServices;
    }

    public void setPriorityServices(List<FeesServices> priorityServices) {
        this.priorityServices = priorityServices;
    }

    public List<FeesServices> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<FeesServices> otherServices) {
        this.otherServices = otherServices;
    }

}
