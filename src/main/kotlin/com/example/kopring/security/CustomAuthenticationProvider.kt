package com.example.kopring.security

import com.example.kopring.dto.UserDetail
import javassist.NotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder

): AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name.toLowerCase()
        val password = (authentication.credentials as String).toLowerCase()
        val userDetail = userDetailsService.loadUserByUsername(name) as UserDetail

        checkPassword(userDetail, password)

        return UsernamePasswordAuthenticationToken(userDetail, null, userDetail.authorities)
    }

    private fun checkPassword(userDto: UserDetail, password: String) {
        check(!isValidPassWord(userDto.password, password)) {
            "비밀번호를 찾을 수 없습니다."
        }
    }

    private fun isValidPassWord(encodingPassword: String, password: String): Boolean {
        return passwordEncoder.matches(password, encodingPassword)
    }

    /*
    * 토큰 타입과 일치할 때 인증
    * */
    override fun supports(authentication: Class<*>?): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }
}