package com.configuration.security.dto;

import java.util.Map;

/**
 * @author avinash.a.mishra
 */
public class AuthResponse {
   private final String jwtToken;
   private Map<String, Object> info;

    public AuthResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public AuthResponse(String jwtToken, Map<String, Object> info) {
        this.jwtToken = jwtToken;
        this.info=info;
    }

    public String getJwtToken() {
        return jwtToken;
    }



    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
}
