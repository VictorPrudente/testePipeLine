package com.dbc.provas.client;

import com.dbc.provas.model.login.LoginRequest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/auth/login";

    public Response login(LoginRequest loginRequest) {
        return step("Enviando a requisição de login com credenciais", () ->
                given()
                    .spec(super.set())
                    .contentType(ContentType.JSON)
                    .body(loginRequest)
                .when()
                    .post(LOGIN)
        );
    }
}
