package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

object TransactionSqlExpressions {
    fun sqlInsert() = """
        INSERT INTO "transaction" (
        id, 
        transactionDate,
        totalValue,
        quantity, 
        user_id, 
        investment_id
        ) VALUES (
        :id, 
        :transactiondate, 
        :totalvalue, 
        :quantity, 
        :user_id, 
        :investment_id
        )
    """.trimIndent()
    fun sqlFindById() = """
        SELECT  
        id, 
        transactionDate,
        totalValue,
        quantity, 
        user_id, 
        investment_id
        FROM "transaction"
        WHERE id = :id
    """.trimIndent()

    fun sqlFindAll() = """
        SELECT  
        id, 
        transactionDate,
        totalValue,
        quantity, 
        user_id, 
        investment_id
        FROM "transaction"
    """.trimIndent()

    fun sqlDelete() = """
        DELETE FROM "transaction" WHERE id = :id
    """.trimIndent()

    fun sqlUpdate() = """
        UPDATE "transaction"
        SET transactionDate = :transactionDate,
            totalValue = :totalvalue, 
            quantity = :quantity, 
            user_id = :user_id, 
            investment_id = :investment_id
    """.trimIndent()
}
