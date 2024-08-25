package com.felipebs.dockadb_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.felipebs.dockadb_api.enuns.estado.ERegiaoBrasil;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "estado")
@JsonPropertyOrder({"nome", "siglaEstado", "siglaRegiao", "regiao_geografica"})
public class Estado {

    @Id
    @JsonIgnore
    private String id;

    private String nome;

    private String siglaEstado;

    private String siglaRegiao;

    @JsonProperty("regiao_geografica")
    public String getRegiaoGeografica() {
        if (siglaRegiao == null || Objects.equals(siglaRegiao, "")) return "";

        var regiao = ERegiaoBrasil.parseSigla(siglaRegiao);

        if (regiao == null) return "";


        return regiao.getSigla() + " - " + regiao.getRegiao();
    }
}
