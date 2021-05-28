package com.example.kopring.controller.dto

data class SignInDto(
    val email: String,
    val password: String,
    val nickname: String
) {
    companion object {

    }
}