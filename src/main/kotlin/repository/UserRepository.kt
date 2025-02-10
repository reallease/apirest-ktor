package com.thomasd.repository

import com.thomasd.models.User
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID


class UserRepository {
    // codico para falar com o banco de dados aqui
    // val listUsers get() = _users.toList() // fazendo uma copia

    fun findAll(): List<User> = _users

    fun findById(id: UUID): User? = // pode ser nulo
        _users.firstOrNull { it.id == id }

    fun findByEmail(email: String): User? =
        _users.firstOrNull { it.email == email }

    private fun encryptPassword(password: String) : String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun save(user: User) {
        val passwordEncrypt = encryptPassword(user.password) // Criptografa a senha
        val userUpdatedWithEncrypt = user.copy(password = passwordEncrypt) // Atualiza a senha criptografada

        _users.add(userUpdatedWithEncrypt)
    }

    private val _users = mutableListOf<User>()

}