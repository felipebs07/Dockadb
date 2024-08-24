package com.felipebs.dockadb_api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "localizacao")
public class Continente {

    @Id
    private String id;

    private String nome;

    private LocalDateTime datCriacao;
}
