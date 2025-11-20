package com.example.bossguer.features.registro.data.source.remote

import com.example.bossguer.core.common.Result
import com.example.bossguer.features.user.domain.model.User

interface IRegisterRemoteDataSource {
    suspend fun register(email: String, password: String): Result<User>
}
