package com.example.kopring.security

import com.example.kopring.util.TokenConstants
import com.example.kopring.util.TokenConstants.TOKEN_HEADER
import com.example.kopring.util.TokenConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken as UsernamePasswordAuthenticationToken1

// 인가
class JwtAuthorizationFilter(authentication: AuthenticationManager) : BasicAuthenticationFilter(authentication) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,
                                  chain: FilterChain) {

        val authentication = getAuthentication(request)
        val header = request.getHeader(TOKEN_HEADER)

        if (header.isEmpty() || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken1? {
        val token = request.getHeader(TOKEN_HEADER)
        if (token.isNotEmpty()) {

            val signingKey = TokenConstants.JWT_SECRET_KEY.toByteArray()
            val parsedToken = Jwts
                .parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token.replace("Bearer ", ""))

            var username = parsedToken
                .body.subject

            val authorities =  (parsedToken.body["rol"] as ArrayList<String>).map { SimpleGrantedAuthority(it) }

            if (username.isNotEmpty()) {
                return UsernamePasswordAuthenticationToken1(username, null, authorities)
            }
        }
        return null
    }
}