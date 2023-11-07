package br.com.carteiradeinvestimentos.application.transaction

import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class TransitionUpdateDTO (
    val transactionDate: String,
    val totalValue: Double,
    val quantity: Int,
    val userId: UUID,
    val investmentId: UUID
)

fun TransitionUpdateDTO.toTransaction(id: UUID) = Transaction (
    id = id,
    transactionDate = transactionDate,
    totalValue = totalValue,
    quantity = quantity,
    userId = userId,
    investmentId = investmentId
)