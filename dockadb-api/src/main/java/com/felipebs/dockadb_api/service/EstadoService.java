package com.felipebs.dockadb_api.service;

import com.felipebs.dockadb_api.dto.GenericoDTO;
import com.felipebs.dockadb_api.entity.Estado;
import com.felipebs.dockadb_api.repository.estado.EstadoCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EstadoService {

    private final EstadoCustomRepository estadoCustomRepository;

    public EstadoService(EstadoCustomRepository estadoCustomRepository) {
        this.estadoCustomRepository = estadoCustomRepository;
    }

    public Page<Estado> buscarEstadoPaginado(String nome, String siglaEstado, String siglaRegiao, Integer page, Integer elementsByPage) {
        return estadoCustomRepository.buscarEstadoPaginado(nome, siglaEstado, siglaRegiao, PageRequest.of(page, elementsByPage));
    }

    public void salvarEstadoScheduler(List<GenericoDTO> mapJson) {

        for (GenericoDTO item : mapJson) {
            System.out.println(item);

            String nome = item.getProperty("nome").toString();
            String siglaEstado = item.getProperty("sigla").toString();

            Map<String, Object> regiao = (Map<String, Object>) item.getProperty("regiao");
            String siglaRegiao = regiao.get("sigla").toString();

            siglaRegiao = siglaRegiao.equals("N") ? "NO" : siglaRegiao;
            siglaRegiao = siglaRegiao.equals("S") ? "SU" : siglaRegiao;

            estadoCustomRepository.salvaSeNaoExiste(Estado.builder()
                    .nome(nome)
                    .siglaEstado(siglaEstado)
                    .siglaRegiao(siglaRegiao)
                    .build());
        }
    }
}
