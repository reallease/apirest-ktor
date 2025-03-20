package com.thomasd.routes

import com.thomasd.dao.UserDao
import com.thomasd.models.User
import com.thomasd.repository.UserRepository
import com.thomasd.routes.request.UserRequest
import com.thomasd.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoute(
    userService: UserService,
    userDao: UserDao
) {
    val repository = UserRepository()
    post {
        val userRequest = call.receive<UserRequest>()
        val createdUser = repository.saveUserDB(userRequest.toModel())

        if (createdUser != null) {
            call.respond(HttpStatusCode.Created, message = "User was created")
        } else {
            call.respond(HttpStatusCode.BadRequest, "User not created")
        }

//        call.response.header( // para o header
//            name = "id",
//            value = createdUser.id.toString()
//        )
    }

    get("/{email}") {
        val email = call.parameters["email"]
        if (email != null) {
            val usss = userDao.findEmailUser(email)
            if (usss != null) {
                call.respond(usss)
            } else {
                call.respond(HttpStatusCode.NotFound, "Usuario nao encontrado")
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Email invalido")
        }
    }

}


private fun UserRequest.toModel(): User =
    User(
        name = this.name,
        email = this.email,
        password = this.password
    )