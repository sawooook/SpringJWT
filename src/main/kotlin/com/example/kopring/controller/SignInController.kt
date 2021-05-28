package com.example.kopring.controller

import com.example.kopring.controller.dto.SignInDto
import com.example.kopring.service.LoginService
import com.example.kopring.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignInController(
    private val userService: UserService,
    private val loginService: LoginService
) {

    @PostMapping
    fun signIn(@RequestBody signInDto: SignInDto) {
        val checkEmail = userService.isDuplicated(signInDto.email)
        if (checkEmail) {

        }
    }
}