package br.com.carteiradeinvestimentos

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Encoders
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarteiradeinvestimentosApplication

fun main(args: Array<String>) {
	val message = Encoders.BASE64.encode(Jwts.SIG.HS512.key().build().encoded)
	println(message)
//runApplication<CarteiradeinvestimentosApplication>(*args)
}



