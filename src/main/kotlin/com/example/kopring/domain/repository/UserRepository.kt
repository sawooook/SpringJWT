package com.example.kopring.domain.repository

import com.example.kopring.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByEmail(email: String): User?
}