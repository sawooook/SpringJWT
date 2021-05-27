//package com.example.kopring.controller
//
//import com.example.kopring.domain.User
//import com.example.kopring.dto.AuthenticationRequest
//import com.example.kopring.dto.AuthenticationResponse
//import com.example.kopring.dto.SignupRequest
//import com.example.kopring.dto.UserDetail
//import com.example.kopring.service.UserService
//import com.example.kopring.util.JwtUtil
//import org.springframework.http.HttpHeaders
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.web.bind.annotation.*
//import kotlin.jvm.Throws
//
//@RestController
//@RequestMapping("/login")
//class AuthenticationController(
//    private val jwtUtil: JwtUtil,
//    private val userService: UserService,
//    private val authenticationManager: AuthenticationManager
//) {
//
//    @PostMapping
//    @Throws(Exception::class)
//    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*>? {
//        val responseHeaders = HttpHeaders()
//        val authenticate: Authentication = authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(authenticationRequest.userName, authenticationRequest.password)
//        )
//
//        val userDetails: UserDetail = userService.UloadUserByUsername(authenticationRequest.userName) as UserDetail
//        val jwt: String = jwtUtil.generateToken(userDetails)
//        val roles: MutableList<String> = userDetails.authorities.map { it.authority }.toMutableList()
//        val response = userDetails.getId()?.let { AuthenticationResponse(it, userDetails.username, jwt, roles) }
//
//        return ResponseEntity<Any?>(response, responseHeaders, HttpStatus.OK)
//    }
//
//    @PostMapping("/signup")
//    @ResponseBody
//    fun signup(@RequestBody signupRequest: SignupRequest) {
//
//        if(userService.existsByName(signupRequest.name)){
//            return ResponseEntity
//                .badRequest()
//                .body(MessageResponse("Error: Username is already taken!"));
//        }
//
//        val user = User(
//            email = signupRequest.name,
//            roles = signupRequest.role,
//            password = BCryptPasswordEncoder().encode(signupRequest.password)
//        )
//
//        userService.save(user)
//
//    }
//}
