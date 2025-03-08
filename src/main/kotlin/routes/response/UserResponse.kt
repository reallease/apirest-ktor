package com.thomasd.routes.response

import com.thomasd.models.User
import com.thomasd.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt
import java.util.*

@Serializable
data class UserResponse(
    //val id: String,
    val name: String,
    val email: String,
    val password: String
)

fun User.toResponse() = UserResponse(
    name = name,
    email = email,
    password = password
)