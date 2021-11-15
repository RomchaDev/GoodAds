package org.romeo.layer_domain.use_cases

import org.romeo.layer_domain.entity.user.User
import org.romeo.layer_domain.repository_bounderies.UserRepository

class LoginUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCase {
    override suspend fun login(username: String, password: String): User {
        val response = userRepository.login(username, password)

        userRepository.saveLoginResponse(response)

        return response.user
    }
}