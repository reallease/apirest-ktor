package com.thomasd.service

import com.thomasd.dao.UserDao
import org.mindrot.jbcrypt.BCrypt

class UserService (
    private val dao: UserDao = UserDao()
){

    // service para tratamentos de dados e verificação de dados

    suspend fun findUserEmail(email: String) = dao.findEmailUser(email)
}