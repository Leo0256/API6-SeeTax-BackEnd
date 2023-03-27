package com.SeeTax.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SeeTax.entity.PersonalAccount.Companies;
import com.SeeTax.entity.PersonalAccount.FeesServices;
import com.SeeTax.entity.PersonalAccount.PersonalAccount;
import com.SeeTax.entity.PersonalAccount.PersonalAccountList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class MainService {

    // precisa mudar a origem para uma que obtêm dados de mais bancos
    private final String base_uri = "https://api.itau/open-banking/products-services/v1";
    private final String personalAccountsURL = base_uri + "/personal-accounts";

    private RestTemplate rest = new RestTemplate();

    /**
     * Converte o valor informado de string para double.
     * 
     * @param value
     * @return Valor convertido em double
     */
    private Double StrToDouble(String value) {
        if(value.equals("NA")) {
            return 0.0;
        }

        return Double.parseDouble(value);
    }

    /**
     * Retorna o ranking dos Top 5 menores tarifas.
     */
    public String getRanking() throws IOException {
        try {
            ResponseEntity<PersonalAccount> responseEntity = rest.getForEntity(personalAccountsURL, PersonalAccount.class);

            // Veio os dados do Open Finance
            if(responseEntity.hasBody() && responseEntity.getBody() != null) {

                // Obtêm os dados
                PersonalAccount data = responseEntity.getBody();

                // Nenhum dado foi Obtido
                if(data == null) throw new NullPointerException("Nenhum dado foi Obtido.");

                // Mapeamento dos objetos json
                ObjectMapper objectMapper = new ObjectMapper();
                
                // Lista dos dados a serem retornados
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
                            Double max = StrToDouble(priorityServices.getMaximum().getValue());
                            Double min = StrToDouble(priorityServices.getMinimum().getValue());

                            // Média dos valores
                            Double media = (max + min)/2;
                            medias.add(media);
                        }

                        // OtherServices
                        for(FeesServices otherServices : personalAccount.getFees().getOtherServices()) {
                            // Valores máximo e mínimo
                            Double max = StrToDouble(otherServices.getMaximum().getValue());
                            Double min = StrToDouble(otherServices.getMinimum().getValue());

                            // Média dos valores
                            Double media = (max + min)/2;
                            medias.add(media);
                        }

                        // Calcula o somatório das médias
                        double sum = 0;
                        for(double i : medias) sum += i;

                        childNode.put("name", companie.getName());
                        childNode.put("cnpj", companie.getCnpjNumber());
                        childNode.put("type", personalAccount.getType());
                        childNode.put("serviceCount", serviceCount);
                        childNode.put("average", String.format("%.2f", sum/medias.size()));

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

                // Top 5 ordenados
                ArrayNode sortedList;

                // Lista dos top 5 menores valores
                if(length <= 5) {
                    sortedList = objectMapper.createArrayNode().addAll(dataList.subList(0, length));
                } else {
                    sortedList = objectMapper.createArrayNode().addAll(dataList.subList(0, 5));
                }

                ObjectNode object = objectMapper.createObjectNode();
                object.put("length", dataList.size());
                object.set("data", sortedList);

                // Retorna os dados em json string
                return objectMapper.writeValueAsString(object);
            }
            // Não foi possível obter os dados do Open Finance
            else throw new NullPointerException("Não foi possível obter os dados do Open Finance.");
        }
        catch(IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String getTax() throws IOException {
        try {
            ResponseEntity<PersonalAccount> responseEntity = rest.getForEntity(personalAccountsURL, PersonalAccount.class);

            // Veio os dados do Open Finance
            if(responseEntity.hasBody() && responseEntity.getBody() != null) {

                // Obtêm os dados
                PersonalAccount data = responseEntity.getBody();

                // Nenhum dado foi Obtido
                if(data == null) throw new NullPointerException("Nenhum dado foi Obtido.");

                // Mapeamento dos objetos json
                ObjectMapper objectMapper = new ObjectMapper();

                // Lista dos dados a serem retornados
                List<JsonNode> dataList = new ArrayList<>();

                

                for(Companies companie : data.getData().getBrand().getCompanies()) {
                    for(PersonalAccountList personalAccount : companie.getPersonalAccounts()) {

                        // Lista dos serviços
                        List<JsonNode> priorityServiceList = new ArrayList<>();
                        List<JsonNode> otherServiceList = new ArrayList<>();

                        // PriorityServices
                        for(FeesServices priorityServices : personalAccount.getFees().getPriorityServices()) {

                            // Item do json
                            ObjectNode childNode = objectMapper.createObjectNode();

                            // Valores máximo e mínimo
                            Double max = StrToDouble(priorityServices.getMaximum().getValue());
                            Double min = StrToDouble(priorityServices.getMinimum().getValue());

                            childNode.put("name", priorityServices.getName());
                            childNode.put("max", max);
                            childNode.put("min", min);

                            priorityServiceList.add(childNode);

                        }

                        // PriorityServices
                        for(FeesServices otherServices : personalAccount.getFees().getOtherServices()) {

                            // Item do json
                            ObjectNode childNode = objectMapper.createObjectNode();

                            // Valores máximo e mínimo
                            Double max = StrToDouble(otherServices.getMaximum().getValue());
                            Double min = StrToDouble(otherServices.getMinimum().getValue());

                            childNode.put("name", otherServices.getName());
                            childNode.put("max", max);
                            childNode.put("min", min);

                            otherServiceList.add(childNode);

                        }

                        ArrayNode priorityServices = objectMapper.createArrayNode().addAll(priorityServiceList.subList(0, priorityServiceList.size()));
                        ArrayNode otherServices = objectMapper.createArrayNode().addAll(otherServiceList.subList(0, otherServiceList.size()));

                        ObjectNode companieNode = objectMapper.createObjectNode();
                        companieNode.put("companie", companie.getName());
                        companieNode.put("cnpf", companie.getCnpjNumber());
                        companieNode.put("accountType", personalAccount.getType());
                        companieNode.set("priorityServices", priorityServices);
                        companieNode.set("otherServices", otherServices);

                        dataList.add(companieNode);
                    }
                }

                return objectMapper.writeValueAsString(dataList);

            }
            // Não foi possível obter os dados do Open Finance
            else throw new NullPointerException("Não foi possível obter os dados do Open Finance.");
        }
        catch(IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
