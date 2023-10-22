package br.com.carteiradeinvestimentos.domain.transaction

import java.util.*


interface TransactionRepository {
    fun insert(transaction: Transaction): Boolean

    fun findById(transitionId: UUID): Transaction?

}