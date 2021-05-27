package com.example.kopring.provider.dto

import com.example.kopring.controller.dto.SignUpDto

data class UserSignUpDto(
    var email: String,
    var encodePassword: String,
    var nickName: String
) {
    companion object {
        fun convert(signUpDto: SignUpDto, encodePassword: String): UserSignUpDto {
            return UserSignUpDto(
                email = signUpDto.email,
                encodePassword = encodePassword,
                nickName = signUpDto.nickName
            )
        }
    }

}