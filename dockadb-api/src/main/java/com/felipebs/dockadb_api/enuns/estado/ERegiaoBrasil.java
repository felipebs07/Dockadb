package com.felipebs.dockadb_api.enuns.estado;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum ERegiaoBrasil {

    NORTE("NO", "Norte"),
    NORDESTE("NE", "Nordeste"),
    CENTRO_OESTE("CO", "Centro-Oeste"),
    SUDESTE("SE", "Sudeste"),
    SUL("SU", "Sul");

    private final String sigla;
    private final String regiao;

    ERegiaoBrasil(String sigla, String regiao) {
        this.sigla = sigla;
        this.regiao = regiao;
    }

    public static ERegiaoBrasil parseSigla(String sigla) {
        if (sigla == null) return null;

        for (var regiao : ERegiaoBrasil.values()) {
            if (regiao.getSigla().equals(sigla)) {
                return regiao;
            }
        }
        return null;
    }

    public static List<Map<String, String>> listEnum() {
        List<Map<String, String>> list = new ArrayList<>();

        for (var item : ERegiaoBrasil.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("sigla", item.getSigla());
            map.put("regiao", item.getRegiao());
            list.add(map);
        }

        return list;
    }

}
