package br.com.carteiradeinvestimentos.domain.user

interface UserRepository {
    fun insert (user: User): Boolean
    fun selectById(id: String): User?
    fun selectByEmail(email: String): User?
    fun selectAll(): List<User>
    fun update(user: User): Boolean
    fun delete(id: String): Boolean
}