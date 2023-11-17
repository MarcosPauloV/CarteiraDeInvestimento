package br.com.carteiradeinvestimentos.adapters.http.security

import br.com.carteiradeinvestimentos.application.user.UserService
import br.com.carteiradeinvestimentos.domain.user.User
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JWTUtil (
    private val userService: UserService
) {
    private val expiration: Long = 24 * 60 * 60 * 1000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun gererateToken (user: User): String? {
        return Jwts.builder()
            .id(user.id.toString())
            .subject(user.email)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSecretKey())
            .compact()
    }

    fun isValid(jwt: String?) : Boolean {
        return try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseEncryptedClaims(jwt)
            true
        } catch ( e : JwtException ) {
            throw e
        }
    }

    fun  getSecretKey() : SecretKey {
        val keyBytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getAuthentication (jwt: String?) : Authentication {
        val username = Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(jwt).payload.subject
        val user = userService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
}