package com.thomasd.repository

import com.thomasd.models.User

class UserRepository {

    val listUsers get() = _users.toList() // fazendo uma copia

    companion object {
        private val _users = mutableListOf<User>()
    }
}