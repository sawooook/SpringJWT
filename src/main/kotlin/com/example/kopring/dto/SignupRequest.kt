package com.example.kopring.dto

import com.example.kopring.domain.UserRole

data class SignupRequest(
    val name: String,
    val password: String,
    val role: String = UserRole.ROLE_USER.role
)