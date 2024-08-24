package com.felipebs.dockadb_api.enuns;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum EGeografiaRegiaoLevel1 {

    AFRICA("2", "Africa"),
    AMERICAS("19", "Americas"),
    ASIA("142", "Asia"),
    EUROPE("150", "Europe"),
    OCEANIA("9", "Oceania");

    private final String codigo;
    private final String nome;

    EGeografiaRegiaoLevel1(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static EGeografiaRegiaoLevel1 parseM49(String codigo) {
        if(codigo == null) return null;

        for (var regiao : EGeografiaRegiaoLevel1.values()) {
            if (regiao.getCodigo().equals(codigo)) {
                return regiao;
            }
        }

        return null;
    }

    public static List<Map<String, String>> listEnum() {
        List<Map<String, String>> list = new ArrayList<>();

        for (var item : EGeografiaRegiaoLevel1.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("codigo", item.getCodigo());
            map.put("nome", item.getNome());
            list.add(map);
        }

        return list;
    }
}
