package br.com.carteiradeinvestimentos.adapters.http.security.exceptions

sealed class CredentialsExceptions (message: String) : Exception (message)
class InvalidCredentialsException : CredentialsExceptions("Invalid credentials")