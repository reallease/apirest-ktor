package com.thomasd.routes

import com.thomasd.dao.UserDao
import com.thomasd.repository.UserRepository
import com.thomasd.service.JwtService
import com.thomasd.service.UserService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService,
    jwtService: JwtService
) {
    routing {
        route("/api/auth") {
            authRoute(jwtService)
        }
        route ("/api/user"){
            userRoute(userService, userDao = UserDao())
        }
    }
}