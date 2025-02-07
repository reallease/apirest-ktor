package com.thomasd.service

import com.thomasd.models.User
import com.thomasd.repository.UserRepository
import java.util.*

class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User> =
        userRepository.findAll()

    fun findById(id: String): User? =
        userRepository.findById(
            id = UUID.fromString(id)
        )

    fun findByEmail(email: String): User? =
        userRepository.findByEmail(email)

    fun save(user: User): User? {
        val foundEmail = findByEmail(user.email)

        return if(foundEmail == null) {
            userRepository.save(user)
            user
        } else null
    }
}