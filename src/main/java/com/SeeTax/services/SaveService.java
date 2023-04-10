package com.SeeTax.services;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SeeTax.entity.Servicos.Servicos;
import com.SeeTax.entity.Servicos.ServicosBody;
import com.SeeTax.repository.ServicosRep;

@Service
public class SaveService {
    
    private final String url = "https://olinda.bcb.gov.br/olinda/servico/Informes_ListaTarifaPorValores/versao/v1/odata/";
    private final String servicos_url = url+ "ServicosBancarios?$top=10000&$format=json";

    private RestTemplate rest = new RestTemplate();

    @Autowired
    private ServicosRep servicosRep;

    /**
     * Salva os serviços no banco de dados.
     * 
     * @throws Exception
     */
    public Boolean saveServicos() throws Exception {
        try {
            ResponseEntity<ServicosBody> responseEntity = rest.getForEntity(
                servicos_url,
                ServicosBody.class
            );

            if(!responseEntity.hasBody()) throw new Exception("Falha de acesso aos dados.");

            ServicosBody body = responseEntity.getBody();

            if(body == null) throw new Exception("Falha na requisição dos dados.");

            List<Servicos> servicos = body.getServicos();

            ExecutorService threadPool = Executors.newCachedThreadPool();

            for(Servicos servico : servicos) {
                threadPool.execute(new Runnable() {

                    @Override
                    public void run() {
                        servicosRep.save(servico);
                    }
                    
                });
            }

            threadPool.shutdown();

            while(!threadPool.isTerminated()) {}
            
            return threadPool.isTerminated();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
