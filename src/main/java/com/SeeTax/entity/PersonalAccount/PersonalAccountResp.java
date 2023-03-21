package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalAccountResp {
    
    @JsonProperty("data")
    private Data data;
    
    @JsonProperty("links")
    private Links links;
    
    @JsonProperty("meta")
    private Meta meta;

    public PersonalAccountResp(Data data, Links links, Meta meta) {
        this.data = data;
        this.links = links;
        this.meta = meta;
    }

    public PersonalAccountResp() {}

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
