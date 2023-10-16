package br.com.carteiradeinvestimentos.domain.transaction

import java.util.*

data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val transactionDate: String,
    val totalValue: Double,
    val quantity: Int,
    val user_id: UUID,
    val investment_id: UUID
)
