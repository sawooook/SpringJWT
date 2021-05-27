package com.example.kopring.util

object TokenConstants {
    val AUTH_LOGIN_URL = "/api/authenticate"

    val JWT_SECRET_KEY = "sawooook"

    val TOKEN_HEADER = "Authorization"
    val TOKEN_PREFIX = "Bearer "
    val TOKEN_TYPE = "JWT"
    val TOKEN_ISSUER = "secure-api"
    val TOKEN_AUDIENCE = "secure-app"
}