package br.com.carteiradeinvestimentos.application.user

interface EncoderPassword {
    fun encode(password: String): String
    fun matches(rawPassword: String, encodedPassword: String): Boolean
}
