package com.thomasd.models

import kotlinx.serialization.Serializable
import java.util.UUID

data class User( // classe somente para o modelo
    val id: UUID,
    val username: String,
    val email: String,
    val password: String
) {

}