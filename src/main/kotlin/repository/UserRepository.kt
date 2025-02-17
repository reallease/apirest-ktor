package com.thomasd.repository

import com.thomasd.dao.UserDao
import com.thomasd.models.User
import com.thomasd.models.Users
import com.thomasd.routes.response.UserResponse
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID


class UserRepository (
    private val dao: UserDao = UserDao()
){
    // codico para falar com o banco de dados aqui
    // val listUsers get() = _users.toList() // fazendo uma copia

    fun findAll(): List<User> = _users

    suspend fun users() = dao.listAllUsers()

    suspend fun saveUserDB(user: User) = dao.saveUserInDB(user)

//    fun getAllUsers(): List<UserResponse> {
//        return transaction {
//            Users.selectAll().map{ linha ->
//                UserResponse(
//                    id = linha[Users.id],
//                    username = linha[Users.username],
//                    email = linha[Users.email],
//                    password = linha[Users.password],
//                )
//            }
//        }
//    }

//    fun findById(id: UUID): User? = // pode ser nulo
//        _users.firstOrNull { it.id == id }

    fun findByEmail(email: String): User? =
        _users.firstOrNull { it.email == email }

//    private fun encryptPassword(password: String) : String {
//        return BCrypt.hashpw(password, BCrypt.gensalt())
//    }
//
//    fun save(user: User) {
//        val passwordEncrypt = encryptPassword(user.password) // Criptografa a senha
//        val userUpdatedWithEncrypt = user.copy(password = passwordEncrypt) // Atualiza a senha criptografada
//
//        _users.add(userUpdatedWithEncrypt)
//    }

    private val _users = mutableListOf<User>()

}