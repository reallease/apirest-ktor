package com.thomasd.repository

import com.thomasd.models.User

class UserRepository {

    val listUsers get() = _users.toList() // fazendo uma copia

    fun save(user: User) {
        _users.add(user)
    }

    companion object {
        private val _users = mutableListOf<User>()
    }
}