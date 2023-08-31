package com.shootCompetition.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shootCompetition.domain.dto.ShootDataDTO;

public class JsonUtil {
    public static String toJson (Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toObject (String str,Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(str,clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
