package com.thomasd.routes.request

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    // criar isso para que o client da api nao gerasse o id
    val username: String,
    val email: String,
    val password: String
)