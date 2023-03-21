package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links {
    
    @JsonProperty("self")
    private String self;
    
    @JsonProperty("first")
    private String first;
    
    @JsonProperty("last")
    private String last;

    public Links(String self, String first, String last) {
        this.self = self;
        this.first = first;
        this.last = last;
    }

    public Links() {}

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
