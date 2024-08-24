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

    // Agendar para executar no primeiro dia de cada mês, à meia-noite
    @Scheduled(fixedDelay = 1000 * 60)
    public void saveCountryScheduler() throws ServicoDadosApiException {
        servicoDadosApi.salvarPaisScheduler();
        servicoDadosApi.salvarEstadoScheduler();
    }
}
