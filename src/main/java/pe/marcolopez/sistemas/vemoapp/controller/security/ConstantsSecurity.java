package pe.marcolopez.sistemas.vemoapp.controller.security;

public class ConstantsSecurity {

    public static final String LOGIN_URL = "/login";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    public static final String ISSUER_INFO = "http://marcolopez.pe";

    public static final String SUPER_SECRET_KEY = "Abc123$$8.x";
    public static final long TOKEN_EXPIRATION_TIME = 86_400_000; // 1 Day
}
