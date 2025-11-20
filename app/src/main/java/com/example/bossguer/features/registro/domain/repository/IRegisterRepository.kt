package com.example.bossguer.features.registro.domain.repository

import com.example.bossguer.core.common.Result
import com.example.bossguer.features.user.domain.model.User

interface IRegisterRepository {
    suspend fun register(email: String, password: String): Result<User>
}
