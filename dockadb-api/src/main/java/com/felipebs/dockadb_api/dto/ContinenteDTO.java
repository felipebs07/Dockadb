package com.felipebs.dockadb_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContinenteDTO {
    private List<Map<String, String>> regiao_geografia_1;
    private List<Map<String, String>> regiao_geografia_2;
}