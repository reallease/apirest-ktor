package com.thomasd

import com.thomasd.database.DatabaseFactory
import com.thomasd.plugins.configureSecurity
import com.thomasd.plugins.configureSerialization
import com.thomasd.repository.UserRepository
import com.thomasd.routes.configureRouting
import com.thomasd.service.JwtService
import com.thomasd.service.UserService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()
    val userRepository = UserRepository()
    val userService = UserService()
    val jwtService = JwtService(this, userService)

    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService, jwtService)
}
