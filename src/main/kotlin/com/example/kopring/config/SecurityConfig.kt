package com.example.kopring.config

import com.example.kopring.security.JwtAuthenticationFilter
import com.example.kopring.security.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
): WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }

    // 보안처리
    override fun configure(http: HttpSecurity) {
        http.cors().and() // 동일한 출처가 아니여도 자원을 허용하도록함.
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login/**").permitAll()
            .anyRequest().authenticated()
            .and()
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationToken::class.java)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("password"))
            .authorities("ROLE_USER")
    }

    // 패스워드 암호화를 위한 Bean
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
