package com.felipebs.dockadb_api.controller;

import com.felipebs.dockadb_api.entity.Continente;
import com.felipebs.dockadb_api.service.ContinenteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/continente")
public class ContinenteController {

    private final ContinenteService continenteService;

    public ContinenteController(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    @GetMapping
    private ResponseEntity<Page<Continente>> findPaginationContinente(@RequestParam(required = false) String nome,
                                                                      @RequestParam Integer page,
                                                                      @RequestParam Integer elementsByPage) {
        Page<Continente> pagination = continenteService.findPaginationContinente(nome, page, elementsByPage);

        if (pagination.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(pagination, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Continente> create(@RequestBody Continente nome) {
        return new ResponseEntity<>(continenteService.create(nome), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private HttpEntity<Object> delete(@PathVariable long id) {
        return continenteService.delete(id);
    }
}
