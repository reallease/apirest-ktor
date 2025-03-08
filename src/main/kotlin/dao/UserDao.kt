package com.thomasd.dao

import com.thomasd.models.User
import com.thomasd.models.Users
import com.thomasd.models.Users.email
import com.thomasd.routes.request.UserRequest
import com.thomasd.routes.response.ResponseDB
import com.thomasd.routes.response.UserResponse
import com.thomasd.service.UserService
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.mindrot.jbcrypt.BCrypt

class UserDao (
    private val userService: UserService = UserService()
) {

    suspend fun listAllUsers(): List<User> = dbQuery {
        Users.selectAll().map {
            User(
                name = it[Users.name],
                email = it[Users.email],
                password = it[Users.password],
            )
        }
    }

    suspend fun saveUserInDB(user: User): User? = dbQuery {
        val passwordEncrypt = userService.encryptPassword(user.password)
        val insertUserDatabase = Users.insert {
            it[name] = user.name // erro estava aqui
            it[email] = user.email
            it[password] = passwordEncrypt
        }
        insertUserDatabase.resultedValues?.singleOrNull()?.let {
            // singleornull garante que seja retornado apenas
            // um valor
            User(
                name = it[Users.name],
                email = it[Users.email],
                password = passwordEncrypt,
            )
        }
    }

    suspend fun findEmailAndPasswordDB(user: String) : User? = dbQuery {
        Users.select { Users.email eq email }
            .map {
                User(
                    name = it[Users.name],
                    email = it[Users.email],
                    password = it[Users.password]
                )
            }.singleOrNull() // retorna 1 unico elemento da lista ou null se nao tiver
    }
}

suspend fun <T> dbQuery(block: suspend() -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }