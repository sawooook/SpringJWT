package com.example.kopring.dto

data class AuthenticationResponse(
    val id: Long,
    val userName: String,
    val jwt: String,
    val roles: List<String>
)