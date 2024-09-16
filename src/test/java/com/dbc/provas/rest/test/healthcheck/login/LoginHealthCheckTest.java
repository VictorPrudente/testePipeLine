package com.dbc.provas.rest.test.healthcheck.login;

import com.dbc.provas.data.factory.LoginDataFactory;
import com.dbc.provas.rest.test.base.LoginBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static com.dbc.provas.story.DisplayName.HEALTH_CHECK_LOGIN;
import static com.dbc.provas.story.Feature.FEATURE_HEALTHCHECK;
import static com.dbc.provas.story.Tags.CENARIO_POSITIVO;
import static com.dbc.provas.story.Tags.HEALTH_CHECK;
import static com.dbc.provas.story.UserStories.*;
import static io.qameta.allure.Allure.step;
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
    @DisplayName(CT_API_LOGIN_01)
    public void testHealthCheck() {
        Allure.label("tag", HEALTH_CHECK);
        Allure.label("tag", CENARIO_POSITIVO);

        step("Validando a resposta da requisição", () -> {
            loginClient.login(loginRequest)
                    .then()
                        .statusCode(HTTP_OK);
        });
    }
}