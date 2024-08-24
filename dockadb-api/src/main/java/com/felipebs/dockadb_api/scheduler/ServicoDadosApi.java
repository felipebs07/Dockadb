package com.felipebs.dockadb_api.scheduler;

import com.felipebs.dockadb_api.exception.ServicoDadosApiException;
import com.felipebs.dockadb_api.service.EstadoService;
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


    String urlPais = "https://servicodados.ibge.gov.br/api/v1/localidades/paises?lang=pt-br";
    String urlEstado = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";

    private HttpClient client() {
       return HttpClient.newHttpClient();
    }

    private HttpRequest request(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public void salvarPaisScheduler() throws ServicoDadosApiException {
        try {
            HttpResponse<String> response = client().send(request(urlPais), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var mapJson = JsonUtil.converterJsonParaMap(response.body());
                paisService.salvarPaisScheduler(mapJson);
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            // Captura IOException e InterruptedException e lança uma ServicoDadosApiException
            throw new ServicoDadosApiException("Error searching for countries", e);
        }
    }

    public void salvarEstadoScheduler() throws ServicoDadosApiException {
        try {
            HttpResponse<String> response = client().send(request(urlEstado), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                var mapJson = JsonUtil.converterJsonParaMap(response.body());
                estadoService.salvarEstadoScheduler(mapJson);
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            // Captura IOException e InterruptedException e lança uma ServicoDadosApiException
            throw new ServicoDadosApiException("Error searching for countries", e);
        }
    }
}
