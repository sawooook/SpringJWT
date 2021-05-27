package com.example.kopring.security


import com.example.kopring.service.LoginService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val loginService: LoginService
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = jwtTokenProvider.getTokenHeader(request)

        if (jwtTokenProvider.isValidated(token)) {
            val userID = jwtTokenProvider.getUserID(token)
            val userDetails = loginService.loadUserByUsername(userID)
        }

    }

}