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

    // persistencia com o banco de dados, a parte de salvar no banco de dados


    suspend fun users() = dao.listAllUsers()

    suspend fun saveUserDB(user: User) = dao.saveUserInDB(user)

}