package br.com.carteiradeinvestimentos.application.user

import br.com.carteiradeinvestimentos.application.user.exception.UserNotFoundException
import br.com.carteiradeinvestimentos.domain.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.UserDetailsService

@Service
class UserService (
    private val userRepository: UserRepository,
    private val encoderPassword: EncoderPassword
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
       val user = userRepository.selectByEmail(username ?: "")
           ?: throw UserNotFoundException(username = username)
        return UserDetailsSpringSecurity(user)
    }

}