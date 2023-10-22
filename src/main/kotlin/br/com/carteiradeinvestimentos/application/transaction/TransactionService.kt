package br.com.carteiradeinvestimentos.application.transaction

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

    fun findById(transactionId: UUID): Transaction? {
        return transitionRepository.findById(transactionId)
    }

}