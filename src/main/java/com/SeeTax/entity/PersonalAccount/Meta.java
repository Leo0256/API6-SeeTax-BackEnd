package com.SeeTax.entity.PersonalAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    
    @JsonProperty("totalRecords")
    private Integer totalRecords;
    
    @JsonProperty("totalPages")
    private Integer totalPages;

    public Meta(Integer totalRecords, Integer totalPages) {
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
    }

    public Meta() {}

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
