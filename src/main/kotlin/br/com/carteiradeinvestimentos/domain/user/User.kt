package br.com.carteiradeinvestimentos.domain.user

import java.util.UUID
data class User (
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val password: String
)