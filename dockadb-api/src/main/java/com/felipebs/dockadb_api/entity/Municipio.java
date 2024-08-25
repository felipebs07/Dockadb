package com.felipebs.dockadb_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "municipio")
@JsonPropertyOrder({"nome", "ibge", "siglaEstado"})
public class Municipio {

    @Id
    @JsonIgnore
    private String id;

    private String nome;

    private String ibge;

    private String siglaEstado;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Estado estado;
}
