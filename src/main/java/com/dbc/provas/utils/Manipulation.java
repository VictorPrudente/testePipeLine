package com.dbc.provas.utils;

import com.dbc.provas.model.login.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulation {
    private static ObjectMapper mapper = new ObjectMapper();

    private static Properties getProp() {
        Properties props = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/main/resources/application.properties");
            props.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    public static LoginRequest getAdmin() {

        String adminJson = getProp().getProperty("admin");

        return Convertions.convertJsonToLoginRequest(adminJson);
    }

    public static LoginRequest getGestor() {

        String adminJson = getProp().getProperty("gestor");

        return Convertions.convertJsonToLoginRequest(adminJson);
    }

    public static LoginRequest getUsuario() {

        String adminJson = getProp().getProperty("usuario");

        return Convertions.convertJsonToLoginRequest(adminJson);
    }
}