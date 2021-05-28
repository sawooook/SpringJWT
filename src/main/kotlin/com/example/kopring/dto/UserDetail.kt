package com.example.kopring.dto

import com.example.kopring.domain.User
import com.example.kopring.domain.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val id: Long?,
    private val username: String,
    private val password: String,
    private val authorities: UserRole
): UserDetails {

    fun getId() = this.id

    override fun getAuthorities(): MutableList<GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(authorities.toString()))
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
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

}