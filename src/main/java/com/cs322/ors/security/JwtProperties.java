package com.cs322.ors.security;

public class JwtProperties {
    public static final String SECRET = "secret";
    public static final int EXPIRATION_TIME = 87_600_000; // 1 day in milliseconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
