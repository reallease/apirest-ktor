package com.thomasd.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.thomasd.com.thomasd.routes.request.LoginRequest
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import org.mindrot.jbcrypt.BCrypt
import java.util.*

class JwtService(
    private val application: Application,
    private val userService: UserService
) {

    private val secret = getConfigProperty("jwt.secret")
    private val issuer = getConfigProperty("jwt.issuer")
    private val audience = getConfigProperty("jwt.audience")

    val realm = getConfigProperty("jwt.realm")

    val jwtVerifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()

    private fun loginRequestPassword(password: String) {
        return
    }

    fun createJwtToken(loginRequest: LoginRequest): String? {
        val foundUser = userService.findByEmail(loginRequest.email)

        // se o usuario foi encontrado e a senha criptografada é igual a senha do usuario
        return if(foundUser != null && BCrypt.checkpw(loginRequest.password, foundUser.password)) {
            JWT
                .create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("email", foundUser.email)
                .withExpiresAt(Date(System.currentTimeMillis() + 3_600_000))
                .sign(Algorithm.HMAC256(secret))
        } else null
    }

    fun customValidator(credential: JWTCredential): JWTPrincipal? {
        val email = extractEmail(credential)
        val foundUser = email.let(userService::findByEmail)

        return foundUser?.let {
            if(audienceMatches(credential)) {
                JWTPrincipal(credential.payload)
            } else null
        }
    }

    private fun audienceMatches(credential: JWTCredential): Boolean =
        credential.payload.audience.contains(audience)

    private fun extractEmail(credential: JWTCredential): String =
        credential.payload.getClaim("email").asString()

    private fun getConfigProperty(path: String) =
        application.environment.config.property(path).getString()
}