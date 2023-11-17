package br.com.carteiradeinvestimentos.application.user

import br.com.carteiradeinvestimentos.domain.user.User
import kotlinx.serialization.Serializable

@Serializable
class UserCreateDTO(
    val name: String,
    val email: String,
    val password: String,
)

fun UserCreateDTO.toUsuario(encoderPassword: EncoderPassword) = User (
    name = name,
    email = email,
    password = encoderPassword.encode(password),
)
