package com.thomasd.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.*


class User( // classe somente para o modelo
    val name: String,
    val email: String,
    val password: String
)

object Users : Table("users_com_id") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val email = varchar("email", 255).uniqueIndex()
    val password = varchar("password", 255)

    override val primaryKey = PrimaryKey(id)
}