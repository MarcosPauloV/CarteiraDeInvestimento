package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

object TransactionSqlExpressions {

    fun sqlInsert() = """"
        INSERT INTO transaction(
            id,
            transactionDate,
            totalValue,
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
    """.trimMargin()

    fun sqlSelectById() = """"
        SELECT  id,
                transactionDate,
                totalValue,
                quantity,
                user_id,
                investment_id
        FROM transaction
        WHERE id = :id
    """.trimMargin()

}