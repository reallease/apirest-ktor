package com.thomasd.plugins

import com.thomasd.service.JwtService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity(
    jwtService: JwtService
) {
    authentication {
        jwt {
            realm = jwtService.realm
            verifier(jwtService.jwtVerifier)

//            validate { credential->
//                jwtService.customValidator(credential)
//            }
        }
    }
}