package com.dbc.provas.data.factory;

import net.datafaker.Faker;

import java.util.Locale;

public abstract class BaseDataFactory {

    private static final Faker faker = new Faker(new Locale("PT-BR"));

}
