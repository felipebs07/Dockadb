package com.felipebs.dockadb_api.enuns.continente;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum EGeografiaRegiaoLevel2 {

    AFRICA_CENTRAL("17", "África Central"),
    AFRICA_NORTE("15", "África do Norte"),
    AFRICA_OCIDENTAL("11", "África Ocidental"),
    AFRICA_ORIENTAL("14", "África Oriental"),
    AFRICA_SUBSAARIANA("202", "África Subsaariana"),
    AMERICA_CENTRAL("13", "América Central"),
    AMERICA_LATINA_CARIBE("419", "América Latina e Caribe"),
    AMERICA_DO_NORTE("21", "América do Norte"),
    ASIA_CENTRAL("143", "Ásia Central"),
    ASIA_MERIDIONAL("34", "Ásia Meridional"),
    ASIA_OCIDENTAL("145", "Ásia Ocidental"),
    ASIA_ORIENTAL("30", "Ásia Oriental"),
    CARIBE("29", "Caribe"),
    EUROPA_MERIDIONAL("39", "Europa Meridional"),
    EUROPA_OCIDENTAL("155", "Europa Ocidental"),
    EUROPA_ORIENTAL("151", "Europa Oriental"),
    EUROPA_SETENTRIONAL("154", "Europa Setentrional"),
    MELANESIA("54", "Melanésia"),
    MICRONESIA("57", "Micronésia"),
    OCEANIA_AUSTRALASIA("53", "Australásia"),
    POLINESIA("61", "Polinésia"),
    SUDESTE_ASIATICO("35", "Sudeste Asiático");

    private final String codigo;
    private final String nome;

    EGeografiaRegiaoLevel2(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static EGeografiaRegiaoLevel2 parseM49(String codigo) {
        if (codigo == null) return null;
        for (var region : EGeografiaRegiaoLevel2.values()) {
            if (region.getCodigo().equals(codigo)) {
                return region;
            }
        }

        return null;
    }

    public static List<Map<String, String>> listEnum() {
        List<Map<String, String>> list = new ArrayList<>();

        for (var item : EGeografiaRegiaoLevel2.values()) {
            Map<String, String> map = new HashMap<>();
            map.put("codigo", item.getCodigo());
            map.put("nome", item.getNome());
            list.add(map);
        }

        return list;
    }
}
