package com.thomasd.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.*


class User( // classe somente para o modelo
    //val id: UUID = UUID.randomUUID(),
    val username: String,
    val email: String,
    val password: String
)

object Users : Table("users_sem_id") {
    //val id = uuid("id").autoGenerate()
    val username = varchar("user_name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)

    //override val primaryKey = PrimaryKey(id)
}