package br.com.carteiradeinvestimentos.application.user.exception

import java.util.UUID

sealed class UserException (message: String): Exception(message) {
    abstract val userId: UUID?
}

data class UserNotFoundException (
    override val userId: UUID? = null,
    val username: String? = null
): UserException("Usuário ${userId ?: username} não encontrado")