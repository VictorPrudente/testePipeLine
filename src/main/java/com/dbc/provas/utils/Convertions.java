package com.dbc.provas.utils;

import com.dbc.provas.model.login.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Convertions {

    private static ObjectMapper mapper = new ObjectMapper();


    public static LoginRequest convertJsonToLoginRequest(String json){
        try {
            return mapper.readValue(json, LoginRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
