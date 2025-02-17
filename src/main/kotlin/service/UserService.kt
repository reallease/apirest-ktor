package com.thomasd.service

import com.thomasd.models.User
import com.thomasd.models.Users
import com.thomasd.repository.UserRepository
import org.jetbrains.exposed.sql.insert
import java.util.*

class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User> =
        userRepository.findAll()

//    fun findById(id: String): User? =
//        userRepository.findById(
//            id = UUID.fromString(id)
//        )

    fun findByEmail(email: String): User? =
        userRepository.findByEmail(email)


    // colocar o Users.insert aqui
//    fun save(user: User): User? {
//        val foundEmail = findByEmail(user.email)
//
//
//        return if(foundEmail == null) {
//            userRepository.save(user)
//            Users.insert {
//                it[id] = id
//                it[username] = username
//                it[email] = email
//                it[password] = password
//            }
//            user
//        } else null
//    }
}