package com.thomasd.dao

import com.thomasd.models.User
import com.thomasd.models.Users
import com.thomasd.routes.response.UserResponse
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class UserDao {
    suspend fun listAllUsers(): List<User> = dbQuery {
        Users.selectAll().map {
            User(
                id = it[Users.id],
                username = it[Users.username],
                email = it[Users.email],
                password = it[Users.password],
            )
        }
    }

    suspend fun save(user: User) {
        val insertUserDatabase = Users.insert {
            it[id] = id
            it[username] = username
            it[email] = email
            it[password] = password
        }
        insertUserDatabase.resultedValues?.singleOrNull()?.let {
            User(
                id = it[Users.id],
                username = it[Users.username],
                email = it[Users.email],
                password = it[Users.password],
            )
        }
    }
}

suspend fun <T> dbQuery(block: suspend() -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }