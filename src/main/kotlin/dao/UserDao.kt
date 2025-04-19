package com.thomasd.dao

import com.thomasd.models.User
import com.thomasd.models.Users
import com.thomasd.routes.response.UserResponseComID
import com.thomasd.service.UserService
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.mindrot.jbcrypt.BCrypt

class UserDao(
) {

    fun encryptPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

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
        val passwordEncrypt = encryptPassword(user.password)

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

    suspend fun findEmailUser(email: String): UserResponseComID? = dbQuery {
        Users.select { Users.email eq email }
            .map {
                UserResponseComID(
                    id = it[Users.id],
                    name = it[Users.name],
                    email = it[Users.email]
                )
            }
            .singleOrNull()// Retorna um único elemento ou null
    }



    suspend fun emailExist(email: String) = dbQuery {
        Users.select { Users.email eq email } // SELECT * FROM users WHERE(email = 'email@gmail.com');
            .limit(1) // limite de 1, porque precisamos so de 1 pra ver se existe
            .count() > 0 // se for encontrado entao eh maior que 0 ent ja tem

    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }