package br.com.carteiradeinvestimentos.domain.transaction

import java.util.*
data class Transaction(
    val id: UUID = UUID.randomUUID(),
    val transactionDate: String,
    val totalValue: Double,
    val quantity: Int,
    val userId: UUID,
    val investmentId: UUID
)