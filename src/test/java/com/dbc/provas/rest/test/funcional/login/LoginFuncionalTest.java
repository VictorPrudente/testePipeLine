package com.dbc.provas.rest.test.funcional.login;

import com.dbc.provas.rest.test.base.LoginBase;
import com.dbc.provas.utils.Manipulation;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.dbc.provas.story.DisplayName.FUNCIONAL_LOGIN;
import static com.dbc.provas.story.Feature.FEATURE_FUNCIONAL;
import static com.dbc.provas.story.Tags.*;
import static com.dbc.provas.story.UserStories.*;
import static io.qameta.allure.Allure.step;
import static java.net.HttpURLConnection.HTTP_OK;

@Epic(EPIC_LOGIN)
@Feature(FEATURE_FUNCIONAL)
@Story(USER_STORY_LOGIN_FUNCIONAL)
@DisplayName(FUNCIONAL_LOGIN)
public class LoginFuncionalTest extends LoginBase {

    @BeforeAll
    public static void Setup() {
        loginRequest = Manipulation.getAdmin();
    }

    @Test
    @DisplayName(CT_API_LOGIN_03)
    @Description("Escrever algo")
    public void testLoginSuccess() {
        Allure.label("tag", FUNCIONAL);
        Allure.label("tag", CENARIO_POSITIVO);

        step("Validando o login na aplicação ao utilizar dados válidos", () ->
                loginClient.login(loginRequest)
                        .then()
                            .statusCode(HTTP_OK));
    }
}
