package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlInsert
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlSelectById
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import br.com.carteiradeinvestimentos.domain.transaction.TransactionRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TransactionJDBCRepository (
        private val db: NamedParameterJdbcOperations
): TransactionRepository {
    override fun insert(transaction: Transaction): Boolean {
        val params = MapSqlParameterSource()
        params.addValue("id", transaction.id)
        params.addValue("transactionDate", transaction.transactionDate)
        params.addValue("totalValue", transaction.totalValue)
        params.addValue("quantity", transaction.quantity)
        params.addValue("user_id", transaction.user_id)
        params.addValue("investment_id", transaction.investment_id)
        val afectedLines = db.update(sqlInsert(), params)
        return afectedLines > 0
    }

    override fun findById(transitionId: UUID): Transaction? {
        val params = MapSqlParameterSource("id", transitionId)
        val transition = db.query(sqlSelectById(), params, rowMapper()).firstOrNull()
        return transition
    }

    private fun rowMapper() = org.springframework.jdbc.core.RowMapper<Transaction> { rs, _ ->
        val transitionId = UUID.fromString(rs.getString("id"))

        Transaction(
            id = transitionId,
            transactionDate = rs.getString("transactionDate"),
            totalValue = rs.getDouble("transactionDate"),
            quantity = rs.getInt("quantity"),
            user_id = UUID.fromString(rs.getString("user_id")),
            investment_id = UUID.fromString(rs.getString("investment_id"))
        )
    }

}
