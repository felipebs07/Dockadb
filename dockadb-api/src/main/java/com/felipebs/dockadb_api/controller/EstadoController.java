package com.felipebs.dockadb_api.controller;

import com.felipebs.dockadb_api.entity.Estado;
import com.felipebs.dockadb_api.service.EstadoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }


    @GetMapping
    public ResponseEntity<Page<Estado>> buscarEstadoPaginado(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String siglaEstado,
            @RequestParam(required = false) String siglaRegiao,
            @RequestParam Integer page,
            @RequestParam Integer elementsByPage) {
        return ResponseEntity.ok(estadoService.buscarEstadoPaginado(nome, siglaEstado, siglaRegiao, page, elementsByPage));
    }
}
