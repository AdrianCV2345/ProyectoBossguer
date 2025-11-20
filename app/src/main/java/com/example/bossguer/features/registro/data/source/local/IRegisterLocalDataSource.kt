package com.example.bossguer.features.registro.data.source.local

import com.example.bossguer.features.user.domain.model.User

interface IRegisterLocalDataSource {
    suspend fun save(user: User)
}
