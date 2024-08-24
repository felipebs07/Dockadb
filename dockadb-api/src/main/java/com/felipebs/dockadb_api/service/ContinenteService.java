package com.felipebs.dockadb_api.service;

import com.felipebs.dockadb_api.entity.Continente;
import com.felipebs.dockadb_api.repository.continenteRy.ContinenteCustomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ContinenteService {

    private final ContinenteCustomRepository continenteRepository;

    public ContinenteService(ContinenteCustomRepository continenteRepository) {
        this.continenteRepository = continenteRepository;
    }

    public Page<Continente> findPaginationContinente(String nome, Integer page, Integer elementsByPage) {
        return continenteRepository.findContinente(nome, PageRequest.of(page, elementsByPage));
    }

    public Continente create(Continente entity) {
        entity.setDatCriacao(LocalDateTime.now());

        return continenteRepository.continenteRepository().save(entity);
    }

    public HttpEntity<Object> delete(long codContinente) {
        try {
            continenteRepository.continenteRepository().deleteById(codContinente);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
