package com.thomasd.routes.response

import com.thomasd.models.User
import kotlinx.serialization.Serializable

data class ResponseDB(
    val email: String,
    val password: String
)

@Serializable
data class UserResponseComID (
    val id: Int,
    val name: String,
    val email: String
)

fun User.DBToResponse() = ResponseDB(
    email = email,
    password = password
)