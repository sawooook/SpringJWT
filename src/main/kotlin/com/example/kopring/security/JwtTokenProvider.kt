package com.example.kopring.security

import io.jsonwebtoken.Jwt
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider {

    fun makeToken(
        id: Long
    ): JwtBuilder {
        val claims = Jwts.claims().setSubject(id.toString())
        claims["roles"] = "USER"
        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .setClaims(claims)
            .setIssuedAt(Date.from(Instant.now()))!!
    }

    fun getToken(token: String): String {
        return token.replace("Bearer", "")
    }

    fun getTokenHeader(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    fun isValidated(token: String?): Boolean {
        if (token.isNullOrEmpty()) return false
        if (token.startsWith("Bearer")) return false

        val claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(getToken(token))

        if (claims.body.expiration.before(Date())) {
            return true
        }
        return false
    }

    fun getUserID(token: String?): String {
        return Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token).body.subject!!
    }

    companion object {
        const val SECRET_KEY = "JWT_KEY"
    }
}
