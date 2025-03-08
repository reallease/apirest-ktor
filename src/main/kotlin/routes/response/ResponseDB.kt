package com.thomasd.routes.response

import com.thomasd.models.User

data class ResponseDB(
    val email: String,
    val password: String
)

fun User.DBToResponse() = ResponseDB(
    email = email,
    password = password
)