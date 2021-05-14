package com.example.kopring.domain

import org.hibernate.validator.constraints.Length
import javax.persistence.*
import javax.validation.constraints.Email
import kotlin.math.min

@Entity
@Table(name = "user")
open class User(
    val email: String,
    var password: String

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}