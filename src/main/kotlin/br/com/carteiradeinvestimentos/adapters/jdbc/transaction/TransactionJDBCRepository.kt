package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlDelete
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlFindAll
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlFindById
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlInsert
import br.com.carteiradeinvestimentos.domain.transaction.Transaction
import br.com.carteiradeinvestimentos.domain.transaction.TransactionRepository
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@Repository
class TransactionJDBCRepository(
    private val db: NamedParameterJdbcOperations
) : TransactionRepository {
    override fun findAll(): List<Transaction> {
        val transactions = db.query(sqlFindAll(), rowMapper())
        return transactions
    }
    override fun insert(transaction: Transaction): Boolean {
        val params = params(transaction)
        val afectedLines = db.update(
            sqlInsert(),
            params
        )
        return afectedLines > 0
    }
    override fun findById(transactionId: UUID): Transaction? {
        val params = MapSqlParameterSource("id", transactionId)
        val transaction = db.query(sqlFindById(), params, rowMapper()).firstOrNull()
        return transaction
    }
    override fun delete(transitionId: UUID): Boolean {
        val params = MapSqlParameterSource("id", transitionId)
        val delitedLines = db.update(sqlDelete(), params)
        return delitedLines == 1
    }

    override fun update(transaction: Transaction): Boolean {
        val afectedLines = db.update(
            sqlInsert(),
            params(transaction)
        )
        return afectedLines > 0
    }

    private fun rowMapper() = org.springframework.jdbc.core.RowMapper<Transaction> { rs, _ ->
        val transitionId = UUID.fromString(rs.getString("id"))
        Transaction(
            id = transitionId,
            transactionDate = rs.getString("transactionDate"),
            totalValue = rs.getDouble("totalValue"),
            quantity = rs.getInt("quantity"),
            user_id = UUID.fromString(rs.getString("user_id")),
            investment_id = UUID.fromString(rs.getString("investment_id"))
        )
    }

    private fun params(transaction: Transaction): MapSqlParameterSource {
        val params = MapSqlParameterSource()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = dateFormat.parse(transaction.transactionDate)?:Instant.now()
        params.addValue("id", transaction.id)
        params.addValue("transactionDate", date)
        params.addValue("totalValue", transaction.totalValue)
        params.addValue("quantity", transaction.quantity)
        params.addValue("user_id", transaction.user_id)
        params.addValue("investment_id", transaction.investment_id)
        return params
    }
}