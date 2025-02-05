package com.thomasd.routes

import com.thomasd.models.User
import com.thomasd.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val repository = UserRepository()
    routing {
        get("/users") {
            call.respond(repository.listUsers)
        }
        post("/auth/register") {
            val users = call.receive<User>() // mando o users com o receive do tipo User
            repository.save(users) // salvo na lista o users que foi enviado na request
            call.respondText("User registered successfully", status = HttpStatusCode.OK) // dou um status 200 de ok
        }
    }
}