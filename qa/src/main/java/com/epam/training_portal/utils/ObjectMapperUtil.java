package com.epam.training_portal.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        //  Configure the ObjectMapper as needed.  For example:
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
