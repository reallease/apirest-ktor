package com.thomasd.routes.request

import com.thomasd.models.User
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    // criar isso para que o client da api nao gerasse o id
    val username: String,
    val email: String,
    val password: String
) {
    fun toUser() = User(
        username = username,
        email = email,
        password = password
    )
}