package com.example.kopring.provider

import com.example.kopring.domain.User
import com.example.kopring.domain.repository.UserRepository
import com.example.kopring.exception.NotFoundException
import com.example.kopring.provider.dto.UserSignUpDto
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class UserProvider(
    private val userRepository: UserRepository
) {
    fun findByEmail(
        email: String
    ): User? {
        return userRepository.findByEmail(email)
    }

    fun save(
        userSignUpDto: UserSignUpDto
    ) =
        userRepository.save(User.from(userSignUpDto))

    fun findByUser(id: Long): User {
        return userRepository.findById(id) ?: throw NotFoundException("not found user")
    }
}