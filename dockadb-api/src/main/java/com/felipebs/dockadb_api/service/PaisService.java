package com.felipebs.dockadb_api.service;

import com.felipebs.dockadb_api.dto.GenericoDTO;
import com.felipebs.dockadb_api.entity.Pais;
import com.felipebs.dockadb_api.repository.pais.PaisCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaisService {

    private final PaisCustomRepository paisCustomRepository;

    public PaisService(PaisCustomRepository paisCustomRepository) {
        this.paisCustomRepository = paisCustomRepository;
    }

    public Page<Pais> buscarPaisPaginado(String nome, Integer page, Integer elementsByPage) {
        return paisCustomRepository.buscarPaisPaginado(nome, PageRequest.of(page, elementsByPage));
    }

    public void salvarPaisScheduler(List<GenericoDTO> mapJson) {

        for (GenericoDTO item : mapJson) {
            Map<String, Object> subRegiao = (Map<String, Object>) item.getProperty("sub-regiao");
            String m49CodeLevel2 = ((Map<String, Object>) subRegiao.get("id")).get("M49").toString();
            // Obter o campo "id" dentro de "sub-regiao"
            String m49CodeLevel1 = ((Map<String, Object>) ((Map<String, Object>) subRegiao.get("regiao")).get("id")).get("M49").toString();
            String nome = item.getProperty("nome").toString();
            //
            paisCustomRepository.salvaSeNaoExiste(Pais.builder()
                    .nome(nome)
                    .m49CodeLevel1(m49CodeLevel1)
                    .m49CodeLevel2(m49CodeLevel2)
                    .build());
        }
    }
}
