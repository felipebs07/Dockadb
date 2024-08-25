package com.felipebs.dockadb_api.service;

import com.felipebs.dockadb_api.dto.GenericoDTO;
import com.felipebs.dockadb_api.entity.Municipio;
import com.felipebs.dockadb_api.repository.municipio.MunicipioCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MunicipioService {

    private final MunicipioCustomRepository municipioCustomRepository;

    public MunicipioService(MunicipioCustomRepository municipioCustomRepository) {
        this.municipioCustomRepository = municipioCustomRepository;
    }

    public Page<Municipio> buscarMunicipioPaginado(String nome, String siglaEstado, String ibge, Integer page, Integer elementsByPage) {
        return municipioCustomRepository.buscarMunicipioPaginado(nome, siglaEstado, ibge, PageRequest.of(page, elementsByPage));
    }

    public Page<Municipio> buscarComEstadoCompleto(String nome, String siglaEstado, Integer page, Integer elementsByPage) {
        return municipioCustomRepository.buscarComEstadoCompleto(nome, siglaEstado, PageRequest.of(page, elementsByPage));
    }

    public void salvarMunicipioScheduler(List<GenericoDTO> mapJson) {
        for (GenericoDTO item : mapJson) {
            Map<String, Object> microrregiao = (Map<String, Object>) item.getProperty("microrregiao");
            Map<String, Object> estado = (Map<String, Object>) ((Map<String, Object>) microrregiao.get("mesorregiao")).get("UF");
            String siglaEstado = estado.get("sigla").toString();
            String ibge = item.getProperty("id").toString();
            String nome = item.getProperty("nome").toString();
            municipioCustomRepository.salvaSeNaoExiste(Municipio.builder()
                    .nome(nome)
                    .siglaEstado(siglaEstado)
                    .ibge(ibge)
                    .build());
        }
    }
}
