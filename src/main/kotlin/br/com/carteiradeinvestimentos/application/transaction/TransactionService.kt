package br.com.carteiradeinvestimentos.application.transaction

import br.com.carteiradeinvestimentos.application.transaction.exceptions.TransactionNotFoundException
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import br.com.carteiradeinvestimentos.domain.transaction.TransactionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService (
    private val transitionRepository: TransactionRepository
) {
    fun insert(transaction: TransitionCreateDTO): Transaction? {
        val transitionDomain = transaction.toTransaction()
        transitionRepository.insert(transaction = transitionDomain)
        return findById(transitionDomain.id)
    }

    fun findAll(): List<Transaction> {
        return transitionRepository.findAll()
    }

    fun findById(transactionId: UUID): Transaction? {
        return transitionRepository.findById(transactionId)
    }

    fun update(transaction: TransitionUpdateDTO, transactionId: UUID): Transaction? {
        transitionRepository.findById(transactionId) ?: throw TransactionNotFoundException (transactionId)
        transitionRepository.update(transaction.toTransaction(transactionId))
        return findById(transactionId)
    }

    fun delete(transitionId: UUID) {
        transitionRepository.delete(transitionId)
    }
}