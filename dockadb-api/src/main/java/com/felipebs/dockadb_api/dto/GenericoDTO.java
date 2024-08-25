package com.felipebs.dockadb_api.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class GenericoDTO {
    private Map<String, Object> genericDTO = new HashMap<>();

    @JsonAnySetter
    public void setProperty(String key, Object value) {
        genericDTO.put(key, value);
    }

    public Object getProperty(String key) {
        return genericDTO.get(key);
    }

    public Map<String, Object> getProperties() {
        return genericDTO;
    }
}
