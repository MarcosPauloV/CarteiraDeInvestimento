package br.com.carteiradeinvestimentos.adapters.http.transaction

import br.com.carteiradeinvestimentos.application.transaction.TransitionCreateDTO
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private const val UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"

@RestController
class TransactionController (
    private val transactionHandler: TransactionHandler
) {

    @PostMapping("/transaction")
    fun insert(@RequestBody transaction: TransitionCreateDTO): ResponseEntity<Transaction>{
        return transactionHandler.insert(transaction)
    }

    @GetMapping("/transaction/{transactionId: $UUID_REGEX}")
    fun findByID(@PathVariable transactionId: String): ResponseEntity<Transaction>{
        return transactionHandler.findById(transactionId)
    }
}