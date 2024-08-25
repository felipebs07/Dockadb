package com.felipebs.dockadb_api.controller;

import com.felipebs.dockadb_api.entity.Estado;
import com.felipebs.dockadb_api.entity.Municipio;
import com.felipebs.dockadb_api.service.EstadoService;
import com.felipebs.dockadb_api.service.MunicipioService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/municipio")
public class MunicipioController {

    private final MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }


    @GetMapping
    public ResponseEntity<Page<Municipio>> buscarMunicipioPaginado(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String siglaEstado,
            @RequestParam(required = false) String ibge,
            @RequestParam Integer page,
            @RequestParam Integer elementsByPage) {
        return ResponseEntity.ok(municipioService.buscarMunicipioPaginado(nome, siglaEstado, ibge, page, elementsByPage));
    }

    @GetMapping("/buscarComEstadoCompleto")
    public ResponseEntity<Page<Municipio>> buscarComEstadoCompleto(
            @RequestParam(required = false) String nome,
            @RequestParam String siglaEstado,
            @RequestParam Integer page,
            @RequestParam Integer elementsByPage) {
        return ResponseEntity.ok(municipioService.buscarComEstadoCompleto(nome, siglaEstado, page, elementsByPage));
    }
}
