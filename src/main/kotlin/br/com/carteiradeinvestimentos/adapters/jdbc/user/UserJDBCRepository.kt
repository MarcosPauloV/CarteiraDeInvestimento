package br.com.carteiradeinvestimentos.adapters.jdbc.user

import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlDelete
import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlInsert
import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlSelectAll
import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlSelectByEmail
import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlSelectById
import br.com.carteiradeinvestimentos.adapters.jdbc.user.UserSqlExpressions.sqlUpdate
import br.com.carteiradeinvestimentos.domain.user.User
import br.com.carteiradeinvestimentos.domain.user.UserRepository
import mu.KotlinLogging
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
class UserJDBCRepository (
    private val db: NamedParameterJdbcOperations
) : UserRepository {
    private companion object {
        val LOGGER = KotlinLogging.logger {  }
    }
    @Transactional(rollbackFor = [Exception::class])
    override fun insert(user: User): Boolean {
        try {
            val params = params(user)
            val affectedLines = db.update(sqlInsert(), params)
            return affectedLines > 0
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao inserir o usuário: ${ex.message}" }
            throw ex
        }
    }

    override fun selectById(id: String): User? {
        val user = try {
            val params = MapSqlParameterSource("id", id)
            db.query(sqlSelectById(), params, rowMapper()).firstOrNull()
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao buscar o usuário: ${ex.message}" }
            throw ex
        }
        return user
    }

    override fun selectByEmail(email: String): User? {
        val user = try {
            val params = MapSqlParameterSource("email", email)
            db.query(sqlSelectByEmail(), params, rowMapper()).firstOrNull()
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao buscar o usuário: ${ex.message}" }
            throw ex
        }
        return user
    }

    override fun selectAll(): List<User> {
        val users = try {
            db.query(sqlSelectAll(), rowMapper())
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao buscar os usuários: ${ex.message}" }
            throw ex
        }
        return users
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun update(user: User): Boolean {
        try {
            val params = params(user)
            val affectedLines = db.update(sqlUpdate(), params)
            return affectedLines > 0
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao atualizar o usuário: ${ex.message}" }
            throw ex
        }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun delete(id: String): Boolean {
        try {
            val params = MapSqlParameterSource("id", id)
            val affectedLines = db.update(sqlDelete(), params)
            return affectedLines == 1
        } catch (ex: Exception) {
            LOGGER.error { "Erro ao deletar o usuário: ${ex.message}" }
            throw ex
        }
    }

    private fun rowMapper () = RowMapper<User> { rs, _ ->
        val userId = UUID.fromString(rs.getString("id"))
        User(
            id = userId,
            name = rs.getString("name"),
            email = rs.getString("email"),
            password = rs.getString("password")
        )
    }

    private fun params (user: User) : MapSqlParameterSource {
        val params = MapSqlParameterSource()
        params.addValue("id", user.id)
        params.addValue("name", user.name)
        params.addValue("email", user.email)
        params.addValue("password", user.password)
        return params
    }
}