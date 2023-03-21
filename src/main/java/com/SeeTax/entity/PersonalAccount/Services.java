package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Services {

    @JsonProperty("code")
    private String code;

    @JsonProperty("chargingTriggerInfo")
    private String chargingTriggerInfo;

    @JsonProperty("eventLimitQuantity")
    private String eventLimitQuantity;

    @JsonProperty("freeEventQuantity")
    private String freeEventQuantity;

    public Services(String code, String chargingTriggerInfo, String eventLimitQuantity, String freeEventQuantity) {
        this.code = code;
        this.chargingTriggerInfo = chargingTriggerInfo;
        this.eventLimitQuantity = eventLimitQuantity;
        this.freeEventQuantity = freeEventQuantity;
    }

    public Services() {}

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

    public String getEventLimitQuantity() {
        return eventLimitQuantity;
    }

    public void setEventLimitQuantity(String eventLimitQuantity) {
        this.eventLimitQuantity = eventLimitQuantity;
    }

    public String getFreeEventQuantity() {
        return freeEventQuantity;
    }

    public void setFreeEventQuantity(String freeEventQuantity) {
        this.freeEventQuantity = freeEventQuantity;
    }

}
