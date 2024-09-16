package com.dbc.provas.data.factory;

import com.dbc.provas.model.login.LoginRequest;
import com.dbc.provas.utils.Manipulation;

public class LoginDataFactory extends BaseDataFactory {

    public static LoginRequest admLogin() {
        return Manipulation.getAdmin();
    }
}
