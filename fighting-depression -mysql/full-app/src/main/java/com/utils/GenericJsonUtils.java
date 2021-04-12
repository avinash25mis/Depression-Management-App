package com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author avinash.a.mishra
 */
public class GenericJsonUtils {

    public static String getJsonString(Map map) {
        String json = "";
        try {
            if (MapUtils.isNotEmpty(map)) {
                ObjectMapper objectMapper = new ObjectMapper();
                json = objectMapper.writeValueAsString(map);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static Map  getDeserisledMap(String json){
        Map<String,String> map=new HashMap<>();
        try{
            if(StringUtils.isNotEmpty(json)) {
                ObjectMapper obj = new ObjectMapper();
                map = obj.readValue(json, Map.class);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}