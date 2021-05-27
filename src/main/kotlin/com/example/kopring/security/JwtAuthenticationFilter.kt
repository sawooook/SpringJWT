package com.example.kopring.security


import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    /*
    * 인증 시도 메소드
    * 1.  username과 password를 얻어오고, UsernamePasswordAuthenticationToken을 생성한다
    * 2. AuthenticationManager에게 인증을 진행하도록 위임한다.
    * 3. AuthenticationManage는 인증을 처리한다 -> 구현체인 ProviderManager가 진행
    * */
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

    }

}