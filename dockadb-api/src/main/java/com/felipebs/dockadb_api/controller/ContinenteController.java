package com.felipebs.dockadb_api.controller;

import com.felipebs.dockadb_api.service.ContinenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/continente")
public class ContinenteController {

    private final ContinenteService continenteService;

    public ContinenteController(ContinenteService continenteService) {
        this.continenteService = continenteService;
    }

    @GetMapping("/buscarGeofrafiaRegiao")
    public ResponseEntity<?> buscarGeofrafiaRegiao(String indRegiao) {

        if (Objects.equals(indRegiao, "1")) {
            return new ResponseEntity<>(continenteService.buscarGeofrafiaRegiao1(), HttpStatus.OK);
        }

        if (Objects.equals(indRegiao, "2")) {
            return new ResponseEntity<>(continenteService.buscarGeofrafiaRegiao2(), HttpStatus.OK);
        }

        return new ResponseEntity<>(continenteService.buscarGeoGrafiaRegiao(), HttpStatus.OK);
    }

}
