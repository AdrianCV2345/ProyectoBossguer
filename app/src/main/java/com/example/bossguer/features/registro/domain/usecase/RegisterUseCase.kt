package com.example.bossguer.features.registro.domain.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class RegisterUseCase(private val auth: FirebaseAuth) {

    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            authResult.user?.let {
                Result.success(it)
            } ?: Result.failure(Exception("Usuario no encontrado despues del registro."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
