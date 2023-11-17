package br.com.carteiradeinvestimentos.adapters.jdbc.transaction

import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlDelete
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlFindAll
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlFindById
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlInsert
import br.com.carteiradeinvestimentos.adapters.jdbc.transaction.TransactionSqlExpressions.sqlUpdate
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
    override fun insert(transaction: Transaction): Boolean {
        val params = params(transaction)
        println("chegamos aqui")
        val affectedLines = db.update(
            sqlInsert(),
            params
        )
        return affectedLines > 0
    }

    override fun findAll(): List<Transaction> {
        return db.query(sqlFindAll(), rowMapper())
    }

    override fun findById(transitionId: UUID): Transaction? {
        val params = MapSqlParameterSource("id", transitionId)
        return db.query(sqlFindById(), params, rowMapper()).firstOrNull()
    }

    override fun update(transaction: Transaction): Boolean {
        val affectedLines = db.update(
            sqlUpdate(),
            params(transaction)
        )
        return affectedLines > 0
    }

    override fun delete(transitionId: UUID): Boolean {
        val params = MapSqlParameterSource("id", transitionId)
        val deletedLines = db.update(sqlDelete(), params)
        return deletedLines == 1
    }

    private fun rowMapper() = org.springframework.jdbc.core.RowMapper<Transaction> { rs, _ ->
        val transitionId = UUID.fromString(rs.getString("id"))
        Transaction(
            id = transitionId,
            transactionDate = rs.getString("transactiondate"),
            totalValue = rs.getDouble("totalvalue"),
            quantity = rs.getInt("quantity"),
            userId = UUID.fromString(rs.getString("user_id")),
            investmentId = UUID.fromString(rs.getString("investment_id"))
        )
    }

    private fun params(transaction: Transaction): MapSqlParameterSource {
        val params = MapSqlParameterSource()
        params.addValue("id", transaction.id)
        params.addValue("transactiondate", transaction.transactionDate)
        params.addValue("totalvalue", transaction.totalValue)
        params.addValue("quantity", transaction.quantity)
        params.addValue("user_id", transaction.userId)
        params.addValue("investment_id", transaction.investmentId)
        return params
    }
}