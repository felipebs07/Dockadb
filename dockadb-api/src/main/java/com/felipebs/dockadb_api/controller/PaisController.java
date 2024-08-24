package com.felipebs.dockadb_api.controller;

import com.felipebs.dockadb_api.entity.Pais;
import com.felipebs.dockadb_api.service.PaisService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pais")
public class PaisController {

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }


    @GetMapping
    public ResponseEntity<Page<Pais>> buscarContinente(@RequestParam(required = false) String nome,
                                                       @RequestParam Integer page,
                                                       @RequestParam Integer elementsByPage) {
        return ResponseEntity.ok(paisService.buscarPaisPaginado(nome, page, elementsByPage));
    }
}
