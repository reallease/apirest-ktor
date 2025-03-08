package com.thomasd.service

import com.thomasd.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt
import java.util.*

class UserService{

    // service para tratamentos de dados e verificação de dados

    fun encryptPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


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