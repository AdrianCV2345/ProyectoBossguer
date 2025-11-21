package com.example.bossguer.features.registro.domain.usecase

import com.example.bossguer.features.informacionPersonal.domain.model.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class SaveUserDataUseCase(private val database: FirebaseDatabase) {

    suspend operator fun invoke(uid: String, user: User): Result<Unit> {
        return try {
            database.getReference("Users").child(uid).setValue(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
