package br.com.carteiradeinvestimentos.adapters.http.user

import br.com.carteiradeinvestimentos.application.user.UserCreateDTO
import br.com.carteiradeinvestimentos.application.user.UserQuerry
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
    private val userHandler: UserHandler
) {
    @PostMapping
    fun insert(@RequestBody user: UserCreateDTO) : ResponseEntity<UserQuerry> {
        return userHandler.insert(user)
    }

    @GetMapping

}