package com.thomasd.routes.response

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserResponse(
    val id: UUID,
    val email: String
)