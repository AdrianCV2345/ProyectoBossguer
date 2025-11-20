package com.example.bossguer.features.registro.domain.usecase

import com.example.bossguer.core.common.Result
import com.example.bossguer.features.registro.domain.repository.IRegisterRepository
import com.example.bossguer.features.user.domain.model.User

class RegisterUseCase(private val repository: IRegisterRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.register(email, password)
    }
}
