package com.example.kopring.service

import com.example.kopring.provider.LoginProvider
import com.example.kopring.provider.dto.LoginAuthDto
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val loginProvider: LoginProvider,
    private val userService: UserService
): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val findUser = userService.findByEmail(email)
        return LoginAuthDto.convert(findUser)
    }
}