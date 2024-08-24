package com.felipebs.dockadb_api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.felipebs.dockadb_api.dto.GenericoDTO;

import java.io.IOException;
import java.util.List;

public class JsonUtil {

    public static List<GenericoDTO> convertJsonToMap(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, GenericoDTO.class));
    }
}
