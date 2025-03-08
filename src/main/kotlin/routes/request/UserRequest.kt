package com.thomasd.routes.request

import com.thomasd.models.User
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    // criar isso para que o client da api nao gerasse o id
    val name: String,
    val email: String,
    val password: String
) {
    fun toUser() = User(
        name = name,
        email = email,
        password = password
    )
}