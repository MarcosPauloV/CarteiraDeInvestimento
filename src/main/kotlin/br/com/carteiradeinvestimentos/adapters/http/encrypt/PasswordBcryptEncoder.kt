package br.com.carteiradeinvestimentos.adapters.http.encrypt

import br.com.carteiradeinvestimentos.application.user.EncoderPassword
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
@Component
class PasswordBcryptEncoder(
    private val passwordEncoder: PasswordEncoder
) : EncoderPassword {
    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }
    override fun matches(rawPassword: String, encodedPassword: String): Boolean {
        return passwordEncoder.matches(rawPassword, encodedPassword)
    }
}
