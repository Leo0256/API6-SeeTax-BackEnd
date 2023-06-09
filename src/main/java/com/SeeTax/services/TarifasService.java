package com.SeeTax.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeeTax.entity.Previsao;
import com.SeeTax.entity.Ranking;
import com.SeeTax.entity.TarifaInstituicao.TarifasInstituicao;
import com.SeeTax.entity.TarifasValor.TarifasValor;
import com.SeeTax.entity.ValoresServicos.ValoresServicos;
import com.SeeTax.repository.TarifasInstituicaoRep;
import com.SeeTax.repository.TarifasValorRep;
import com.SeeTax.repository.ValoresServicosRep;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class TarifasService {

    @Autowired
    private TarifasInstituicaoRep tarifasInstRep;

    @Autowired
    private TarifasValorRep tarifasValorRep;

    @Autowired
    private ValoresServicosRep vServicosRep;

    public List<TarifasInstituicao> getTarifasInstituicao() {
        return tarifasInstRep.findAll();
    }

    public List<TarifasValor> getTarifasValor() {
        return tarifasValorRep.findAll();
    }

    public List<ValoresServicos> getTarifasServicos() {
        return vServicosRep.findAll();
    }

    public List<ValoresServicos> getComparadorTarifas(List<String> grupos, List<String> servicos) {
        List<ValoresServicos> valores = vServicosRep.findAll();
        List<ValoresServicos> filter = new ArrayList<>();

        // Possui grupos para filtrar
        if(grupos.size() > 0) {
            for (ValoresServicos valoresServico : valores) {
                // Filtra por grupo
                if(grupos.contains(valoresServico.getGrupo())) {
                    filter.add(valoresServico);
                }
            }
        }
        // Sem filtro por grupo
        else {
            filter.addAll(valores);
        }

        // Possui serviços para filtrar
        if(servicos.size() > 0) {
            return filter.stream().filter(a -> servicos.contains(a.getCodigo())).toList();
        }

        // Sem filtro por serviços
        return filter;
    }

    public List<Ranking> getRanking(String servico) {
        List<TarifasInstituicao> tarifas = tarifasInstRep.findAll();
        List<Ranking> rankingList = new ArrayList<>();

        // Filtra por serviço
        if(!servico.isEmpty()) {
            tarifas.removeIf(a -> (!a.getCodigo().equals(servico)));
        }

        tarifas.sort(new Comparator<TarifasInstituicao>() {

            @Override
            public int compare(TarifasInstituicao inst1, TarifasInstituicao inst2) {
                Float tarifa1 = inst1.getValor_max();
                Float tarifa2 = inst2.getValor_max();

                return tarifa1.compareTo(tarifa2);
            }
            
        });

        int count = 1;
        for (TarifasInstituicao tarifa : tarifas) {
            rankingList.add(new Ranking(count++, tarifa));
            if(count == 5) break;
        }

        return rankingList;
    }

    public List<Previsao> getPrevisao(String cnpj, String servico) throws Exception {
        try {
            List<TarifasValor> tarifas = tarifasValorRep.findByCnpjAndCodigo(
                cnpj, servico
            );
    
            String strOutput = null;
            List<Previsao> list = new ArrayList<>();

            if(tarifas.size() != 0) {
    
                tarifas.forEach(tarifa -> {
                    list.add(new Previsao(
                        tarifa.getData(),
                        String.valueOf(tarifa.getValor_max())
                    ));
                });

                ObjectMapper mapper = new ObjectMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);

                String data = mapper.writeValueAsString(list)
                    .replace("\r\n", "").replace(" ", "").replace("\"", "'");
                String model = "src/main/java/com/SeeTax/modelo.py";

                Process process = Runtime.getRuntime().exec("python " + model + " " + data);

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                while((strOutput = stdInput.readLine()) != null) {
                    System.out.println(strOutput);
                    list.add(mapper.readValue(strOutput.replace("\'", "\""), Previsao.class));
                }

                if(stdError.readLine() != null) {
                    String e = "";
                    while((strOutput = stdError.readLine()) != null) {
                        e.concat(strOutput);
                        System.out.println(strOutput);
                    }
                    throw new Exception(e);
                }
            }

            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
}
