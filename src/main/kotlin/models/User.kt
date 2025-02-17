package com.thomasd.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.UUID

data class User( // classe somente para o modelo
    val id: UUID,
    val username: String,
    val email: String,
    val password: String
)

object Users : Table("users") {
    val id = uuid("id").autoGenerate()
    val username = varchar("user_name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)

    override val primaryKey = PrimaryKey(id)
}