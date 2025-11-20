package com.example.bossguer.features.loginPart.domain

import com.example.bossguer.features.loginPart.data.LoginPartRepository

class LoginPartUseCase(private val repository: LoginPartRepository) {
    suspend fun login(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }
}
