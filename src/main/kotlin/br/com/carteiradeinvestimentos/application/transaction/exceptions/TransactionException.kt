package br.com.carteiradeinvestimentos.application.transaction.exceptions

import java.util.*

sealed class TransactionException (message: String) : Exception(message){
    abstract val transactionId : UUID?
}

data class TransactionNotFoundException (
    override val transactionId : UUID?
) : TransactionException ("Transação $transactionId não encontrada")