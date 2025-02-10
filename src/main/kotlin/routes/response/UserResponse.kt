package com.thomasd.routes.response

import com.thomasd.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt
import java.util.*

@Serializable
data class UserResponse(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val email: String,
    val password: String
)