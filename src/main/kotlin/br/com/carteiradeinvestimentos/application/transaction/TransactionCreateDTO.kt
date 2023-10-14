package br.com.carteiradeinvestimentos.application.transaction

import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TransitionCreateDTO (
    val transactionDate: String,
    val totalValue: Double,
    val quantity: Int,
    val user_id: UUID,
    val investment_id: UUID
)

fun TransitionCreateDTO.toTransaction() = Transaction (
    transactionDate = transactionDate,
    totalValue = totalValue,
    quantity = quantity,
    user_id = user_id,
    investment_id = investment_id
)