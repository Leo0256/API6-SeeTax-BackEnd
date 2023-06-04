package com.SeeTax.services;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.SeeTax.entity.GruposConsolidados.*;
import com.SeeTax.entity.Instituicoes.*;
import com.SeeTax.entity.Servicos.*;
import com.SeeTax.entity.TarifaInstituicao.*;
import com.SeeTax.entity.TarifasValor.*;
import com.SeeTax.entity.ValoresServicos.*;
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
    private TarifasInstituicaoRep tarifasInstRep;

    @Autowired
    private TarifasValorRep tarifasValorRep;

    @Autowired
    private ValoresServicosRep vServicosRep;

    /**
     * Salva os serviços no banco de dados.
     * 
     * @throws Exception
     */
    public Boolean saveServicos() throws Exception {
        try {
            String servicos_url = tarifaPorValor_url + "ServicosBancarios?$top=200&$format=json";

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
    public void saveInst(MultipartFile multipartFile) throws Exception {
        File file = File.createTempFile("temp", ".temp");
        file.createNewFile();
        multipartFile.transferTo(Paths.get(file.getAbsolutePath()));

        List<Instituicao> list = new ArrayList<>();

        try(Scanner scanner = new Scanner(Paths.get(file.getAbsolutePath()))) {
            scanner.nextLine();

            while(scanner.hasNextLine()) {
                String[] lineArray = scanner.nextLine().split(";");
                list.add(new Instituicao(
                    lineArray[0],
                    lineArray[1]
                ));
            }

            instRep.saveAll(list);
        }
    }

    public void saveTarifasInstituicoes() throws Exception {
        try {
            String _url = olinda_uri + "Informes_ListaTarifasPorInstituicaoFinanceira/versao/v1/odata/ListaTarifasPorInstituicaoFinanceira";
            String _url_end = "?$top=100&$format=json";

            List<Instituicao> instituicoes = instRep.findAll();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

            for (Instituicao instituicao : instituicoes) {
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

    public void saveTarifasServicos() throws Exception {
        try {

            List<Grupos> grupos = gruposRep.findAll();
            
            String _url = olinda_uri + "Informes_ListaValoresDeServicoBancario/versao/v1/odata/ListaValoresServicoBancario";
            String _url_end = "?$top=20&$format=json";

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

            for (Grupos grupo : grupos) {
                ResponseEntity<ValoresServicosBody> respPessoaF = rest.getForEntity(
                    _url + "(PessoaFisicaOuJuridica='F',CodigoGrupoConsolidado='" + grupo.getCodigo() + "')" + _url_end,
                    ValoresServicosBody.class
                );

                ResponseEntity<ValoresServicosBody> respPessoaJ = rest.getForEntity(
                    _url + "(PessoaFisicaOuJuridica='J',CodigoGrupoConsolidado='" + grupo.getCodigo() + "')" + _url_end,
                    ValoresServicosBody.class
                );


                if(!respPessoaF.hasBody() || !respPessoaJ.hasBody()) throw new Exception("Falha de acesso aos dados.");
                
                ValoresServicosBody tarifasFBody = respPessoaF.getBody();
                ValoresServicosBody tarifasJBody = respPessoaF.getBody();

                if(tarifasFBody == null || tarifasJBody == null) throw new Exception("Falha na requisição dos dados.");

                List<ValoresServicos> vServicosF = tarifasFBody.getServicos();
                List<ValoresServicos> vServicosJ = tarifasJBody.getServicos();

                executor.submit(() -> {
                    for (ValoresServicos vServicoF : vServicosF) {
                        Optional<Servicos> a = servicosRep.findByNome(vServicoF.getNome_servico());
                        if(a.isPresent() && !a.isEmpty()) {
                            vServicoF.setCodigo(a.get().getCodigo());
                            vServicoF.setPessoa('F');
                            vServicoF.setGrupo(grupo.getCodigo());
                            
                            vServicosRep.save(vServicoF);
                        }
                    }
                });

                executor.submit(() -> {
                    for (ValoresServicos vServicoJ : vServicosJ) {
                        Optional<Servicos> a = servicosRep.findByNome(vServicoJ.getNome_servico());
                        if(a.isPresent() && !a.isEmpty()) {
                            vServicoJ.setCodigo(a.get().getCodigo());
                            vServicoJ.setPessoa('J');
                            vServicoJ.setGrupo(grupo.getCodigo());

                            vServicosRep.save(vServicoJ);
                        }
                        
                    }
                });
            }

            executor.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveTarifasValor(MultipartFile multipartfile) throws Exception {
        if(multipartfile != null) {
            File file = File.createTempFile("temp", ".tmp");
            file.createNewFile();
            multipartfile.transferTo(Paths.get(file.getAbsolutePath()));

            try(Scanner scanner = new Scanner(Paths.get(file.getAbsolutePath()))) {
                
                for(int a = 0; a < 1; a++) scanner.nextLine();

                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

                while(scanner.hasNextLine()) {
                    String[] lineArray = scanner.nextLine().split(";");

                    executor.submit(() -> {
                        TarifasValor tarifaValor = new TarifasValor();

                        String cnpj = lineArray[3].trim();
                        while(cnpj.length() < 8) {
                            cnpj = "0" + cnpj;
                        }
                        
                        tarifaValor.setCnpj(cnpj);
                        tarifaValor.setCodigo(lineArray[1].trim().replace(".", ""));
                        tarifaValor.setPeriodicidade(lineArray[6].trim());
                        tarifaValor.setPessoa(lineArray[7].trim().toCharArray()[0]);
                        tarifaValor.setValor_max(Float.parseFloat(
                            lineArray[5].trim()
                            .replaceAll("\\.", "")
                            .replaceAll("\\,", ".")
                        ));
                        

                        tarifaValor.setData(lineArray[0].trim());

                        tarifasValorRep.save(tarifaValor);
                    });
                }

                scanner.close();

                executor.shutdown();
                while (!executor.isTerminated()) {}
            }
            catch(Exception e) {
                e.getStackTrace();
                System.out.print(e);
            }
        }
        else throw new Exception("Nenhum arquivo informado");
    }

}
