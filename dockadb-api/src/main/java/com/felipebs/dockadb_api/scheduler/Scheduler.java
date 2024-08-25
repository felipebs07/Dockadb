package com.felipebs.dockadb_api.scheduler;

import com.felipebs.dockadb_api.exception.ServicoDadosApiException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final ServicoDadosApi servicoDadosApi;

    public Scheduler(ServicoDadosApi servicoDadosApi) {
        this.servicoDadosApi = servicoDadosApi;
    }

    // Executa uma vez por ano (1º de janeiro às 00:00)
    @Scheduled(cron = "0 0 0 1 1 *")
    public void saveCountryScheduler() throws ServicoDadosApiException {
        servicoDadosApi.salvarPaisScheduler();
        servicoDadosApi.salvarEstadoScheduler();
        servicoDadosApi.salvarMunicipioScheduler();
    }
}
