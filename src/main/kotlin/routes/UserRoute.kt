package com.thomasd.routes

import com.thomasd.com.thomasd.routes.request.UserRequest
import com.thomasd.models.User
import com.thomasd.repository.UserRepository
import com.thomasd.routes.response.UserResponse
import com.thomasd.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.userRoute(
    userService: UserService
) {
    val repository = UserRepository()
    post {
        val userRequest = call.receive<UserRequest>()


        val createdUser = userService.save(
            user = userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header( // para o header
            name = "id",
            value = createdUser.id.toString()
        )
        call.respond(HttpStatusCode.Created, message = "User was created")
    }

    get {
//        val users = userService.findAll()

        val response = repository.users().map {
            it.toResponse()
        }
        call.respond(response)

//        val users2 = userService
//
//        call.respond(
//            message = users.map(User::toResponse)
//        )

    }

    get("/{id}") {
        val id: String = call.parameters["id"]
            ?: return@get call.respond(HttpStatusCode.BadRequest)

            val foundUser = userService.findById(id)
            ?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond(
            message = foundUser.toResponse()
        )
    }
}


private fun UserRequest.toModel(): User =
    User(
        id = UUID.randomUUID(),
        username = this.username,
        email = this.email,
        password = this.password
    )

private fun User.toResponse(): UserResponse =
    UserResponse(
        id = this.id,
        username = this.username,
        email = this.email,
        password = this.password
    )