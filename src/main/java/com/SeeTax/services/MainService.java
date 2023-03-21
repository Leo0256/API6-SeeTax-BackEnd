package com.SeeTax.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SeeTax.entity.PersonalAccount.PersonalAccountResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MainService {
    
    /* @Autowired
    private PersonalAccountResp personalAccount; */

    public String getPersonalAccount() {
        final String uri = "https://api.itau/open-banking/products-services/v1/personal-accounts";

        RestTemplate rest = new RestTemplate();
        ResponseEntity<PersonalAccountResp> a = rest.getForEntity(uri, PersonalAccountResp.class);

        //System.out.println("X: " + a.getBody().getData().getBrand().getName());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String x = objectMapper.writeValueAsString(a.getBody());
            return x;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "veio algo";
        

        //return a.getBody().getData().getBrand().getName();
    }
}
