package com.thomasd.repository

import com.thomasd.models.User
import java.util.UUID

class UserRepository {
    // codico para falar com o banco de dados aqui
    // val listUsers get() = _users.toList() // fazendo uma copia

    fun findAll(): List<User> = _users

    fun findById(id: UUID): User? = // pode ser nulo
        _users.firstOrNull { it.id == id }

    fun findByEmail(email: String): User? =
        _users.firstOrNull { it.email == email }

    fun save(user: User): Boolean =
        _users.add(user)

    private val _users = mutableListOf<User>()

}