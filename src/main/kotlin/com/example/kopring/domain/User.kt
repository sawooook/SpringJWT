package com.example.kopring.domain

import com.example.kopring.provider.dto.UserSignUpDto
import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import kotlin.math.min

@Entity
@Table(name = "user")
class User(
    var email: String,
    var password: String,
    var nickName: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    companion object {
        fun from(userSignUpDto: UserSignUpDto): User {
            return User(
                email = userSignUpDto.email,
                password = userSignUpDto.encodePassword,
                nickName = userSignUpDto.nickName
            )
        }
    }
}