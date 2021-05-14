package com.example.kopring.config

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    // 보안예외처리 ( 정적리소스 , HTML )
    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }

    // 보안처리
    override fun configure(http: HttpSecurity) {
        http.cors().and() // 동일한 출처가 아니여도 자원을 허용하도록함.
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/public").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilter()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}