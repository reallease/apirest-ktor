package com.thomasd.routes

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
            userRoute(userService)
        }
//        get("/users") {
//            call.respond("d")
//        }
//        post("/auth/register") {
//            val users = call.receive<User>() // mando o users com o receive do tipo User
//            repository.save(users) // salvo na lista o users que foi enviado na request
//            call.respondText("User registered successfully", status = HttpStatusCode.OK) // dou um status 200 de ok
//        }
    }
}