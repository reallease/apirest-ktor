package com.thomasd.routes

import com.thomasd.com.thomasd.routes.request.LoginRequest
import com.thomasd.service.JwtService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.math.log

fun Route.authRoute(jwtService: JwtService) {

//    post {
//        val loginRequest = call.receive<LoginRequest>() // recebemos a solicitação de request
//
//        val token = jwtService.createJwtToken(loginRequest) // criamos o token, que retorna uma string ou nulo
//
//        token?.let {
//            call.respond(hashMapOf("token" to it))
//        } ?: call.respond(HttpStatusCode.Unauthorized)
//    }
}