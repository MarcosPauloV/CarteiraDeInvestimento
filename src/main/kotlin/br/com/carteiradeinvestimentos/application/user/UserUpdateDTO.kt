package br.com.carteiradeinvestimentos.application.user

import br.com.carteiradeinvestimentos.domain.user.User
import kotlinx.serialization.Serializable

@Serializable
class UserUpdateDTO (
    val name: String,
    val email: String,
    val password: String
)

fun UserUpdateDTO.toDomain() = User (
    name = name,
    email = email,
    password = password
)