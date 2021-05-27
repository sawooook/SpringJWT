package com.example.kopring.controller

import com.example.kopring.controller.dto.SignUpDto
import com.example.kopring.service.LoginService
import com.example.kopring.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController (
    private val userService: UserService,
) {

    @PostMapping
    fun signUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<Nothing> {
        userService.signUp(signUpDto)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/check/email")
    fun checkEmail(@RequestParam email: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(
            userService.isDuplicated(email)
        )
    }
}