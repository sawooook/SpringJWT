package com.example.kopring.provider.dto

import com.example.kopring.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class LoginAuthDto(
    var id: Long,
    var nickName: String,
    var email: String,
    private val password: String
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return nickName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        fun convert(findUser: User): LoginAuthDto {
            return LoginAuthDto(
                id = findUser.id!!,
                email = findUser.email,
                password = findUser.password,
                nickName = findUser.nickName
            )
        }
    }
}