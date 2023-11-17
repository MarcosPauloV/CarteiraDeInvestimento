package br.com.carteiradeinvestimentos.application.user

import br.com.carteiradeinvestimentos.domain.user.User
import kotlinx.serialization.Serializable

@Serializable
data class UserQuerry (
    val id: String,
    val name: String,
    val email: String
)

fun User.toQuerry() = UserQuerry(
    id = id.toString(),
    name = name,
    email = email
)