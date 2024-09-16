package com.dbc.provas.client;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

import static io.qameta.allure.Allure.step;

public class BaseClient {

    final String BASE_URI = "https://provas-back-hml.onrender.com";

    public RequestSpecification set() {

        return step("Configurando a especificação base da requisição", () ->
                new RequestSpecBuilder()
                        .setBaseUri(BASE_URI)
                        .setConfig(RestAssured.config().logConfig(
                                LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                        .build());
    }
}