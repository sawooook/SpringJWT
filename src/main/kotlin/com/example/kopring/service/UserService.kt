package com.example.kopring.service

import com.example.kopring.controller.dto.SignUpDto
import com.example.kopring.domain.User
import com.example.kopring.provider.UserProvider
import com.example.kopring.provider.dto.UserSignUpDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userProvider: UserProvider,
    private val passwordEncoder: PasswordEncoder
    )
{
    fun signUp(signUpDto: SignUpDto) {
        validatedSignUP(signUpDto)
        userProvider.save(
            UserSignUpDto.convert(
                signUpDto,
                passwordEncoder.encode(signUpDto.password)
            )
        )
    }

    private fun validatedSignUP(signUpDto: SignUpDto) {
        userProvider.findByEmail(signUpDto.email)
    }

    fun isDuplicated(email: String): Boolean {
        check(email == "") {
            "check email address"
        }
        if (userProvider.findByEmail(email) != null) {
            return false
        }

        return true
    }

    fun findByUser(id: Long): User {
        return userProvider.findByUser(id)
    }
}