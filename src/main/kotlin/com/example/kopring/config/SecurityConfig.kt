package com.example.kopring.config

import com.example.kopring.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
): WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }

    /*
    * 보안처리
    * 동일한 출처가 아니여도 자원을 허용하도록함. -> cors
    * csrf -> loginPage delete
    *
    * */
    override fun configure(http: HttpSecurity) {
        http
            .cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(*PERMIT_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider()
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

    companion object {
        val PERMIT_URL = arrayOf(
            "/login/**",
            "/sign-up/**"
        )
    }
}
