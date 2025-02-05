package com.thomasd.models

import kotlinx.serialization.Serializable

@Serializable
class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String
) {

}