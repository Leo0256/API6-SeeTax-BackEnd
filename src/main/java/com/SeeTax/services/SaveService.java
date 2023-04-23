package com.SeeTax.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SeeTax.entity.InstituicaoGrupo;
import com.SeeTax.entity.GruposConsolidados.*;
import com.SeeTax.entity.Instituicoes.*;
import com.SeeTax.entity.Servicos.*;
import com.SeeTax.entity.TarifaInstituicao.*;
import com.SeeTax.entity.TarifasValor.TarifasValor;
import com.SeeTax.entity.TarifasValor.TarifasValorBody;
import com.SeeTax.repository.*;

@Service
public class SaveService {
    
    private final String olinda_uri = "https://olinda.bcb.gov.br/olinda/servico/";

    private final String tarifaPorValor_url = olinda_uri + "Informes_ListaTarifaPorValores/versao/v1/odata/";


    private RestTemplate rest = new RestTemplate();

    @Autowired
    private ServicosRep servicosRep;

    @Autowired
    private GruposRep gruposRep;

    @Autowired
    private InstituicoesRep instRep;

    @Autowired
    private InstituicaoGrupoRep instGrupoRep;

    @Autowired
    private TarifasInstituicaoRep tarifasInstRep;

    @Autowired
    private TarifasValorRep tarifasValorRep;

    /**
     * Salva os serviços no banco de dados.
     * 
     * @throws Exception
     */
    public Boolean saveServicos() throws Exception {
        try {
            String servicos_url = tarifaPorValor_url + "ServicosBancarios?$top=10000&$format=json";

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

    /**
     * Salva os grupos consolidados no banco de dados.
     * 
     * @throws Exception
     */
    public Boolean saveGrupos() throws Exception {
        try {
            String gconso_url = tarifaPorValor_url + "GruposConsolidados?$top=10000&$format=json";

            ResponseEntity<GruposBody> responseEntity = rest.getForEntity(
                gconso_url,
                GruposBody.class
            );

            if(!responseEntity.hasBody()) throw new Exception("Falha de acesso aos dados.");

            GruposBody body = responseEntity.getBody();

            if(body == null) throw new Exception("Falha na requisição dos dados.");

            List<Grupos> grupos = body.getGrupos();

            ExecutorService threadPool = Executors.newCachedThreadPool();

            for(Grupos grupo : grupos) {
                threadPool.execute(new Runnable() {

                    @Override
                    public void run() {
                        gruposRep.save(grupo);
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

    /**
     * Salva as instituições no banco de dados.
     * 
     * @throws Exception
     */
    public void saveInstituicoes() throws Exception {
        try {

            // Grupos consolidados
            List<Grupos> grupos = gruposRep.findAll();

            // URL dos dados das instituições
            String _url = olinda_uri + "Informes_ListaTarifasPorInstituicaoFinanceira/versao/v1/odata/ListaInstituicoesDeGrupoConsolidado";
            String _url_end = "?$top=20&$format=json";

            for (Grupos grupo : grupos) {
                // Código do grupo consolidado
                String grupoCodigo = grupo.getCodigo();

                ResponseEntity<InstituicoesBody> resp = rest.getForEntity(
                    _url + "(CodigoGrupoConsolidado='" + grupoCodigo + "')" + _url_end,
                    InstituicoesBody.class
                );

                if(!resp.hasBody()) throw new Exception("Falha de acesso aos dados.");

                InstituicoesBody body = resp.getBody();

                if(body == null) throw new Exception("Falha na requisição dos dados.");

                // Instituições
                List<Instituicoes> instituicoes = body.getInstituicoes();

                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

                // Salva as instituições
                for (Instituicoes instituicao : instituicoes) {
                    executor.submit(() -> {
                        Optional<Instituicoes> opInstituicao = instRep.findByCnpj(instituicao.getCnpj());
                        InstituicaoGrupo instGrupo = new InstituicaoGrupo();

                        instGrupo.setGrupo(grupo);

                        if(opInstituicao.isEmpty()) {
                            Instituicoes aux = instRep.save(instituicao);
                            instGrupo.setInstituicao(aux);
                        }
                        else {
                            instGrupo.setInstituicao(opInstituicao.get());
                        }


                        Optional<InstituicaoGrupo> opInstGrupo = instGrupoRep.find(
                            instGrupo.getGrupo(),
                            instGrupo.getInstituicao()
                        );
                        if(opInstGrupo.isEmpty()) {
                            instGrupoRep.save(instGrupo);
                        }
                    });
                }

                executor.shutdown();
                while (!executor.isTerminated()) {}
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveTarifasInstituicoes() throws Exception {
        try {
            String _url = olinda_uri + "Informes_ListaTarifasPorInstituicaoFinanceira/versao/v1/odata/ListaTarifasPorInstituicaoFinanceira";
            String _url_end = "?$top=100&$format=json";

            List<Instituicoes> instituicoes = instRep.findAll();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

            for (Instituicoes instituicao : instituicoes) {
                ResponseEntity<TarifasInstituicaoBody> respPessoaF = rest.getForEntity(
                    _url + "(PessoaFisicaOuJuridica='F',CNPJ='" + instituicao.getCnpj() + "')" + _url_end,
                    TarifasInstituicaoBody.class
                );

                if(!respPessoaF.hasBody()) throw new Exception("Falha de acesso aos dados.");
                
                TarifasInstituicaoBody tarifasFBody = respPessoaF.getBody();
                if(tarifasFBody == null) throw new Exception("Falha na requisição dos dados.");


                ResponseEntity<TarifasInstituicaoBody> respPessoaJ = rest.getForEntity(
                    _url + "(PessoaFisicaOuJuridica='J',CNPJ='" + instituicao.getCnpj() + "')" + _url_end,
                    TarifasInstituicaoBody.class
                );

                if(!respPessoaJ.hasBody()) throw new Exception("Falha de acesso aos dados.");

                TarifasInstituicaoBody tarifasJBody = respPessoaJ.getBody();
                if(tarifasJBody == null) throw new Exception("Falha na requisição dos dados.");


                List<TarifasInstituicao> tarifasF = tarifasFBody.getTarifas();
                List<TarifasInstituicao> tarifasJ = tarifasJBody.getTarifas();
                
                executor.submit(() -> {
                    for (TarifasInstituicao tarifa : tarifasF) {
                        tarifa.setCnpj(instituicao.getCnpj());
                        tarifa.setPessoa('F');

                        tarifasInstRep.save(tarifa);
                    }

                    for (TarifasInstituicao tarifa : tarifasJ) {
                        tarifa.setCnpj(instituicao.getCnpj());
                        tarifa.setPessoa('J');

                        tarifasInstRep.save(tarifa);
                    }
                });
            }

            executor.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveTarifasValor() throws Exception {
        try {

            List<Grupos> grupos = gruposRep.findAll();
            List<Servicos> servicos = servicosRep.findAll();

            String _url = olinda_uri + "Informes_ListaTarifaPorValores/versao/v1/odata/ListaTarifasPorValores";
            String _url_end = "?$top=20&$format=json";

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

            for (Grupos grupo : grupos) {
                for (Servicos servico : servicos) {

                    ResponseEntity<TarifasValorBody> resp = rest.getForEntity(
                        _url + "(CodigoGrupoConsolidado='" + grupo.getCodigo() +
                        "',CodigoServico='" + servico.getCodigo() + "')" + _url_end,
                        TarifasValorBody.class
                    );

                    if(!resp.hasBody()) throw new Exception("Falha de acesso aos dados.");

                    TarifasValorBody body = resp.getBody();

                    if(body == null) throw new Exception("Falha na requisição dos dados.");

                    List<TarifasValor> tarifas = body.getTarifas();

                    executor.submit(() -> {
                        for (TarifasValor tarifa : tarifas) {
                            tarifa.setCodigo(servico.getCodigo());
                            tarifa.setGrupo(grupo.getCodigo());

                            tarifasValorRep.save(tarifa);
                        }
                    });
                }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
