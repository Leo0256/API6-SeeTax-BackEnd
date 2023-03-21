package com.SeeTax.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SeeTax.entity.PersonalAccount.Companies;
import com.SeeTax.entity.PersonalAccount.Data;
import com.SeeTax.entity.PersonalAccount.FeesServices;
import com.SeeTax.entity.PersonalAccount.PersonalAccount;
import com.SeeTax.entity.PersonalAccount.PersonalAccountList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MainService {

    private final String base_uri = "https://api.itau/open-banking/products-services/v1/";
    private final String personalAccountsURL = base_uri + "/personal-accounts";

    private RestTemplate rest = new RestTemplate();
    
    /* @Autowired
    private PersonalAccountResp personalAccount; */

    public String getPersonalAccount() {
        ResponseEntity<PersonalAccount> a = rest.getForEntity(personalAccountsURL, PersonalAccount.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String x = objectMapper.writeValueAsString(a.getBody());
            return x;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "veio algo";
        }
    }

    private Double MinMaxConvert(String value) {
        if(value.equals("NA")) {
            return 0.0;
        }

        return Double.parseDouble(value);
    }

    /**
     * @param {number} aioushd
     * @return
     */
    public String getRanking() {
        try {
            ResponseEntity<PersonalAccount> responseEntity = rest.getForEntity(personalAccountsURL, PersonalAccount.class);

            PersonalAccount data = responseEntity.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            
            
            //ArrayNode data_list = objectMapper.createArrayNode();
            List<JsonNode> dataList = new ArrayList<>();

            for(Companies companie : data.getData().getBrand().getCompanies()) {
                for(PersonalAccountList personalAccount : companie.getPersonalAccounts()) {

                    // Total de serviços
                    // PriorityServices.size() + OtherServices.size()
                    Integer serviceCount = personalAccount.getFees().getPriorityServices().size() + personalAccount.getFees().getOtherServices().size();

                    // Lista das médias
                    List<Double> medias = new ArrayList<>();

                    // Item (json) do array
                    ObjectNode childNode = objectMapper.createObjectNode();
                    
                    // PriorityServices
                    for(FeesServices priorityServices : personalAccount.getFees().getPriorityServices()) {

                        // Valores máximo e mínimo
                        Double max = MinMaxConvert(priorityServices.getMaximum().getValue());
                        Double min = MinMaxConvert(priorityServices.getMinimum().getValue());

                        // Média dos valores
                        Double media = (max + min)/2;
                        medias.add(media);
                    }

                    // OtherServices
                    for(FeesServices otheServices : personalAccount.getFees().getOtherServices()) {
                        // Valores máximo e mínimo
                        Double max = MinMaxConvert(otheServices.getMaximum().getValue());
                        Double min = MinMaxConvert(otheServices.getMinimum().getValue());

                        // Média dos valores
                        Double media = (max + min)/2;
                        medias.add(media);
                    }

                    // Calcula o somatório das médias
                    double sum = 0;
                    for(double i : medias) {
                        sum += i;
                    }

                    childNode.put("name", companie.getName());
                    childNode.put("cnpj", companie.getCnpjNumber());
                    childNode.put("type", personalAccount.getType());
                    childNode.put("serviceCount", serviceCount);
                    childNode.put("average", String.format("%.2f", sum/medias.size()));

                    //data_list.add(childNode);
                    dataList.add(childNode);
                }
            }
            
            // Ordenar pelos menores valores médios ("average")
            dataList.sort(new Comparator<JsonNode>() {

                @Override
                public int compare(JsonNode jnode1, JsonNode jnode2) {
                    String v1 = jnode1.get("average").toString();
                    String v2 = jnode2.get("average").toString();

                    return v1.compareTo(v2);
                }
                
            });

            // Total de itens
            int length = dataList.size();

            ArrayNode sortedList;

            // Lista dos top 5 menores valores
            if(length <= 5) {
                sortedList = objectMapper.createArrayNode().addAll(dataList.subList(0, length));
            }
            else {
                sortedList = objectMapper.createArrayNode().addAll(dataList.subList(0, 5));
            }

            ObjectNode object = objectMapper.createObjectNode();
            object.put("length", dataList.size());
            object.set("data", sortedList);

            return objectMapper.writeValueAsString(object);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return "vazio";
    }
}
