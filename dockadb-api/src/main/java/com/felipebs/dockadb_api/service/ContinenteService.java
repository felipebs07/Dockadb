package com.felipebs.dockadb_api.service;

import com.felipebs.dockadb_api.dto.ContinenteDTO;
import com.felipebs.dockadb_api.enuns.continente.EGeografiaRegiaoLevel1;
import com.felipebs.dockadb_api.enuns.continente.EGeografiaRegiaoLevel2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ContinenteService {

    public List<Map<String, String>> buscarGeofrafiaRegiao1() {
        return EGeografiaRegiaoLevel1.listEnum();
    }

    public List<Map<String, String>> buscarGeofrafiaRegiao2() {
        return EGeografiaRegiaoLevel2.listEnum();
    }

    public ContinenteDTO buscarGeoGrafiaRegiao() {
        return ContinenteDTO.builder()
                .regiao_geografia_1(EGeografiaRegiaoLevel1.listEnum())
                .regiao_geografia_2(EGeografiaRegiaoLevel2.listEnum())
                .build();
    }
}
