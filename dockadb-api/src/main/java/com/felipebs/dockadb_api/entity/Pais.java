package com.felipebs.dockadb_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.felipebs.dockadb_api.enuns.EGeografiaRegiaoLevel1;
import com.felipebs.dockadb_api.enuns.EGeografiaRegiaoLevel2;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({ "nome", "regiaoGeografica1", "regiaoGeografica2" })
@Document(collection = "localizacao")
public class Pais {

    @Id
    @JsonIgnore
    private String id;

    private String nome;

    @JsonIgnore
    private String m49CodeLevel1;

    @JsonIgnore
    private String m49CodeLevel2;

    @JsonProperty("regiao_geografia_1")
    public String getRegiaoGeografica1() {
        if (m49CodeLevel1 == null || Objects.equals(m49CodeLevel1, "")) return "";

        var regiao = EGeografiaRegiaoLevel1.parseM49(m49CodeLevel1);

        if (regiao == null) return "";


        return regiao.getCodigo() +  " - "+ regiao.getNome();
    }

    @JsonProperty("regiao_geografia_2")
    public String getRegiaoGeografica2() {
        if (m49CodeLevel2 == null || Objects.equals(m49CodeLevel2, "")) return "";

        var regiao = EGeografiaRegiaoLevel2.parseM49(m49CodeLevel2);

        if (regiao == null) return "";

        return regiao.getCodigo() +  " - "+ regiao.getNome();
    }
}
