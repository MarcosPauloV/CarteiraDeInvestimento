package br.com.carteiradeinvestimentos.adapters.http.transaction

import br.com.carteiradeinvestimentos.application.transaction.TransactionService
import br.com.carteiradeinvestimentos.application.transaction.TransitionCreateDTO
import br.com.carteiradeinvestimentos.application.transaction.TransitionUpdateDTO
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionHandler (
    private val transactionService: TransactionService
) {

    fun findAll(): ResponseEntity<List<Transaction>>{
        val transactions = transactionService.findAll()
        return ResponseEntity.ok(transactions)
    }

    fun insert(transitionCreateDTO: TransitionCreateDTO): ResponseEntity<Transaction>{
        val transaction = transactionService.insert(transitionCreateDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
    }

    fun findById(transactionId: String): ResponseEntity<Transaction>{
        val transaction = transactionService.findById(UUID.fromString(transactionId))
        return ResponseEntity.ok(transaction)
    }

    fun delete(transitionId: String): ResponseEntity<String>{
        transactionService.delete(transitionId = UUID.fromString(transitionId))
        return ResponseEntity.noContent().build()
    }

    fun update(transactionUpdateDTO: TransitionUpdateDTO, id: String): ResponseEntity<Transaction>{
        val transaction = transactionService.update(transactionUpdateDTO, UUID.fromString(id))
        return ResponseEntity.ok(transaction)
    }
}