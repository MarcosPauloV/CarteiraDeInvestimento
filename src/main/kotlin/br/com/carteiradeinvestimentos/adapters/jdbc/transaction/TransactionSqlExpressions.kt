package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

object TransactionSqlExpressions {
    fun sqlInsert() = """
        INSERT INTO transaction( id,  
            transactiondate,
            totalvalue, 
            quantity, 
            user_id, 
            investment_id
        ) values (
            :id,
            :transactionDate,
            :totalValue,
            :quantity,
            :user_id,
            :investment_id
        )
    """.trimIndent()
    fun sqlFindById() = """
        SELECT  * FROM transaction WHERE id = :id
    """.trimIndent()

    fun sqlFindAll() = """
        SELECT * FROM transaction
    """.trimIndent()

    fun sqlDelete() = """
        DELETE FROM transaction WHERE id = :id
    """.trimIndent()

    fun sqlUpdate() = """
        UPDATE transaction
        SET transactiondate = :transactionDate,
            totalvalue = :totalValue, 
            quantity = :quantity, 
            user_id = :user_id, 
            investment_id = :investment_id
    """.trimIndent()

}