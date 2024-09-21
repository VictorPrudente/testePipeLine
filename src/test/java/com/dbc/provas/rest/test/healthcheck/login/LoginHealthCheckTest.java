package com.dbc.provas.rest.test.healthcheck.login;

import com.dbc.provas.data.factory.LoginDataFactory;
import com.dbc.provas.rest.test.base.LoginBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.hc.core5.http2.nio.command.PingCommand;
import org.junit.jupiter.api.*;

import static com.dbc.provas.story.DisplayName.HEALTH_CHECK_LOGIN;
import static com.dbc.provas.story.Feature.FEATURE_HEALTHCHECK;
import static com.dbc.provas.story.UserStories.*;
import static io.qameta.allure.Allure.step;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_HEALTH_CHECK)
@Feature(FEATURE_HEALTHCHECK)
@DisplayName(HEALTH_CHECK_LOGIN)
public class LoginHealthCheckTest extends LoginBase {


    @BeforeAll
    public static void setUp() {
        loginRequest = LoginDataFactory.admLogin();
    }

    @Test
    @Tags({
            @Tag("Health-Check"),
            @Tag("Aceitacao"),
            @Tag("Cenário-Positivo")
    })
    @DisplayName(CT_API_LOGIN_01)
    public void testHealthCheck() {

        step("Validando a resposta da requisição", () -> {
            loginClient.login(loginRequest)
                    .then()
                    .statusCode(HTTP_OK);
        });
    }


}