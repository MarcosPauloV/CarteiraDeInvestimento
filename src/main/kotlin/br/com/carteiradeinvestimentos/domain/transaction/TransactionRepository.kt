package br.com.carteiradeinvestimentos.domain.transaction

import java.util.*


interface TransactionRepository {
    fun findAll(): List<Transaction>
    fun insert(transaction: Transaction): Boolean
    fun findById(transitionId: UUID): Transaction?

    fun delete(transitionId: UUID): Boolean
}