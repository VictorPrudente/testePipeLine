package com.dbc.provas.rest.test.base;

import com.dbc.provas.client.LoginClient;
import com.dbc.provas.model.login.LoginRequest;
import com.dbc.provas.model.login.LoginResponse;

public class LoginBase {
    protected static final LoginClient loginClient = new LoginClient();
    protected static LoginRequest loginRequest;
    protected static LoginResponse loginResponse;

}
