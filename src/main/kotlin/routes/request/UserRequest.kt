package com.thomasd.com.thomasd.routes.request

import com.thomasd.models.User
import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserRequest(
    // criar isso para que o client da api nao gerasse o id
    val username: String,
    val email: String,
    val password: String
)