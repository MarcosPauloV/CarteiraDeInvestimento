package br.com.carteiradeinvestimentos.adapters.jdbc.user

object UserSqlExpressions {
    fun sqlInsert() = """
        INSERT INTO users (
        id,
        name,
        email,
        password
        ) VALUES (
        :id,
        :name,
        :email,
        :password
        )
    """.trimIndent()
    fun sqlSelectAll() = """
        SELECT
            id,
            name,
            email,
            password
        FROM users
    """.trimIndent()
    fun sqlSelectById() = """
        SELECT
            id,
            name,
            email,
            password
        FROM users
        WHERE id = :id
    """.trimIndent()
    fun sqlSelectByEmail() = """
        SELECT
            id,
            name,
            email,
            password
        FROM users
        WHERE email = :email
    """.trimIndent()
    fun sqlUpdate() = """
        UPDATE users SET
            name = :name,
            email = :email,
            password = :password
        WHERE id = :id
    """.trimIndent()
    fun sqlDelete() = """
        DELETE FROM users
        WHERE id = :id
    """.trimIndent()
}