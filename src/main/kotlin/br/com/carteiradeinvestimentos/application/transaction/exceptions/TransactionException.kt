package br.com.carteiradeinvestimentos.application.transaction.exceptions

import java.util.*

sealed class TransactionException (mensage: String) {
    abstract val transactionId : UUID?
}

data class TransactionNotFound (
    override val transactionId : UUID?
) : TransactionException ("Transação $transactionId não encontrada")