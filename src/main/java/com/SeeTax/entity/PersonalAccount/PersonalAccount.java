package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalAccount {
    
    @JsonProperty("data")
    private Data data;
    
    @JsonProperty("links")
    private Links links;
    
    @JsonProperty("meta")
    private Meta meta;

    public PersonalAccount(Data data, Links links, Meta meta) {
        this.data = data;
        this.links = links;
        this.meta = meta;
    }

    public PersonalAccount() {}

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
