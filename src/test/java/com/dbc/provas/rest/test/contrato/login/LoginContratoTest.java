package com.dbc.provas.rest.test.contrato.login;

import com.dbc.provas.rest.test.base.LoginBase;
import com.dbc.provas.utils.Manipulation;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.dbc.provas.story.DisplayName.CONTRATO_LOGIN;
import static com.dbc.provas.story.Feature.FEATURE_CONTRATO;
import static com.dbc.provas.story.UserStories.*;
import static io.qameta.allure.Allure.step;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.matchesPattern;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_CONTRATO)
@Feature(FEATURE_CONTRATO)
@DisplayName(CONTRATO_LOGIN)
public class LoginContratoTest extends LoginBase {


    @BeforeAll
    public static void setUp() {
        loginRequest = Manipulation.getAdmin();
    }

    @Test
    @Tags({@Tag("Contrato"), @Tag("Cenário_Positivo"), @Tag("Aceitacao")})
    @DisplayName(CT_API_LOGIN_02)
    public void testLoginSuccess() {

        step("Validando o contrato de resposta de Login ao utilizar dados válidos", () ->
                loginClient.login(loginRequest)
                        .then()
                        .statusCode(HTTP_OK)
                        .body(matchesPattern("^Bearer\\s[A-Za-z0-9-_\\.]+$")));
    }
}
