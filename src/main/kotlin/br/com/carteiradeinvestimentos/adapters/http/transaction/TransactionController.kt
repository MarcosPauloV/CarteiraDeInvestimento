package br.com.carteiradeinvestimentos.adapters.http.transaction

import br.com.carteiradeinvestimentos.application.transaction.TransitionCreateDTO
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TransactionController (
    private val transactionHandler: TransactionHandler
) {
    @GetMapping("/transactions")
    fun findAll(): ResponseEntity<List<Transaction>>{
        return transactionHandler.findAll()
    }

    @PostMapping("/transaction")
    fun insert(@RequestBody transaction: TransitionCreateDTO): ResponseEntity<Transaction>{
        return transactionHandler.insert(transaction)
    }

    @GetMapping("/transaction/{transactionId}")
    fun findByID(@PathVariable transactionId: String): ResponseEntity<Transaction>{
        return transactionHandler.findById(transactionId)
    }

    @DeleteMapping("/transaction/{transactionId}")
    fun delete(@PathVariable transactionId: String): ResponseEntity<String>{
        return transactionHandler.delete(transactionId)
    }


}