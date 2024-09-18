package com.dbc.provas.story;

public class UserStories {

    public static final String EPIC_LOGIN = "Login";
    public static final String USER_STORY_LOGIN_FUNCIONAL = "Como usuário do sistema, desejo fazer login na aplicação quando inserir email e senha validos";
    public static final String USER_STORY_LOGIN_CONTRATO = "Como usuário do sistema, desejo ter minha credencial validada ao inserir meu email e senha validos";
    public static final String USER_STORY_LOGIN_HEALTH_CHECK = "Como usuário do sistema, desejo acessar a página de login e ter acesso a ela";
    public static final String CT_API_LOGIN_01 = "CT-API-01.01.01 - Validar Status do endpoint Login";
    public static final String CT_API_LOGIN_02 = "CT-API-01.02.01 - Teste de Contrato";
    public static final String CT_API_LOGIN_03 = "CT-API-01.03.01 - Deveria logar com sucesso utilizando credenciais válidas";
    public static final String CT_API_LOGIN_04 = "CT-API-01.03.02 - Deveria logar sem sucesso ao utilizar um email com tamanho excedendo o limite";
    public static final String CT_API_LOGIN_05 = "CT-API-01.03.03 - Deveria logar sem sucesso ao utilizar um email em branco";
    public static final String CT_API_LOGIN_06 = "CT-API-01.03.04 - Deveria logar sem sucesso ao utilizar um email mal formatado";
    public static final String CT_API_LOGIN_07 = "CT-API-01.03.04 - Deveria logar sem sucesso ao utilizar um email mal formatado";
    //Estruturação dos textos -
}
