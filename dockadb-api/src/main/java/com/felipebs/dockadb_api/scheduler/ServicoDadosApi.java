package com.felipebs.dockadb_api.scheduler;

import com.felipebs.dockadb_api.exception.ServicoDadosApiException;
import com.felipebs.dockadb_api.service.EstadoService;
import com.felipebs.dockadb_api.service.MunicipioService;
import com.felipebs.dockadb_api.service.PaisService;
import com.felipebs.dockadb_api.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ServicoDadosApi {

    @Autowired
    private PaisService paisService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private MunicipioService municipioService;


    String urlPais = "https://servicodados.ibge.gov.br/api/v1/localidades/paises?lang=pt-br";
    String urlEstado = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";
    String urlMunicipio = "https://servicodados.ibge.gov.br/api/v1/localidades/municipios?orderBy=nome";

    private HttpClient client() {
        return HttpClient.newHttpClient();
    }

    private HttpRequest request(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    private HttpResponse<String> getHttpDadosBasicos(String url) throws ServicoDadosApiException {
        try {
            return client().send(request(url), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ServicoDadosApiException("Ocorreu um erro ao tentar buscar os dados", e);
        }
    }

    public void salvarPaisScheduler() {
        try {
            var response = getHttpDadosBasicos(urlPais);
            if (response.statusCode() == 200) {
                var mapJson = JsonUtil.converterJsonParaMap(response.body());
                paisService.salvarPaisScheduler(mapJson);
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
            }
        } catch (ServicoDadosApiException | IOException e) {
            e.getStackTrace();
        }
    }

    public void salvarEstadoScheduler() throws ServicoDadosApiException {
        try {
            var response = getHttpDadosBasicos(urlEstado);
            if (response.statusCode() == 200) {
                var mapJson = JsonUtil.converterJsonParaMap(response.body());
                estadoService.salvarEstadoScheduler(mapJson);
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
            }
        } catch (ServicoDadosApiException | IOException e) {
            e.getStackTrace();
        }
    }

    public void salvarMunicipioScheduler() throws ServicoDadosApiException {
        try {
            var response = getHttpDadosBasicos(urlMunicipio);
            if (response.statusCode() == 200) {
                var mapJson = JsonUtil.converterJsonParaMap(response.body());
                municipioService.salvarMunicipioScheduler(mapJson);
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
            }
        } catch (ServicoDadosApiException | IOException e) {
            e.getStackTrace();
        }
    }
}
