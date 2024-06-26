package com.aluracursos.literalura.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectMapperParser implements DataParserProvider {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public <T> T jsonToClass(String data, Class<T> customClass) {
        try {
            return objectMapper.readValue(data, customClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
