package com.example.demo.security;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users-ws/users/register";
    public static final String H2_URL = "/users-ws/h2-console/**";
    public static final String LOGIN_URL = "/users-ws/users/login";
    public static final String API_ACTUATOR_URL = "/users-ws/actuator/*";
    public static final String ZUUL_ACTUATOR_URL = "/actuator/*";
}
