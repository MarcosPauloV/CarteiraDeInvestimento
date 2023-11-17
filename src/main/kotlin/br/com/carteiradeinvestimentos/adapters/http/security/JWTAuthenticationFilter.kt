package br.com.carteiradeinvestimentos.adapters.http.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter (
    private val jwtUtil: JWTUtil,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {

        } catch (e: Exception) {
            val errorResponse = e.toRw
        }
    }

    private fun getTokenDetail(token : String?) : String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, jwt.length)
        }
    }

}